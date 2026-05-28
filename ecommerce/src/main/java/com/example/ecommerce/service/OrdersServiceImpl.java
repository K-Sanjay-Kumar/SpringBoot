package com.example.ecommerce.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.CartItems;
import com.example.ecommerce.model.OrderItems;
import com.example.ecommerce.model.Orders;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.OrdersRepository;

@Service
public class OrdersServiceImpl implements OrdersService {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrdersRepository ordersRepo;
	
	public Orders placeOrder(Long userId) {
		Cart cart=cartService.getCartByUser(userId);
		if(cart==null || cart.getCartItems().isEmpty()) {
			throw new RuntimeException("No products in the Cart");
		}
		
		Orders order=new Orders();
		order.setUser(cart.getUser());
		order.setStatus("PLACED");
		order.setOrderDate(LocalDateTime.now());
		
		double total=0;
		for(CartItems cartItem: cart.getCartItems()) {
			OrderItems orderItem=new OrderItems();
			orderItem.setOrder(order);
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setPrice(cartItem.getProduct().getPrice());
			orderItem.setSubtotal(cartItem.getSubtotal());
			
			Product product=cartItem.getProduct();
			
			product.setStockQuantity(product.getStockQuantity()-cartItem.getQuantity());
			productService.saveAllProduct(product);
			
			total+=cartItem.getSubtotal();
			order.getOrderItems().add(orderItem);
		}
		
		order.setTotalAmount(total);
		ordersRepo.save(order);
		cartService.clearCart(userId);
		
		return order;
	}
	
	public List<Orders> getOrdersByUser(Long userId){
		Orders orders=ordersRepo.findByUserId(userId);
		if(orders==null) {
			throw new RuntimeException("User did not place any order");
		}
		
		return userService.getUserById(userId).getOrder();
	}
	
}
