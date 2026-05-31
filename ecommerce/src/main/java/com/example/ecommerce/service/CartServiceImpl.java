package com.example.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.exception.ResourceNotFoundException;
import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.CartItems;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.Users;
import com.example.ecommerce.repository.CartItemsRepository;
import com.example.ecommerce.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private CartItemsRepository cartItemRepo;

	@Override
	public Cart addProductToCart(Long userId, Long productId, int quantity) {
//		checking user exists for not 
		Users user=userService.getUserById(userId);
		if(user==null) {
			throw new ResourceNotFoundException("User Not Found");
		}
		
//		checking product
		Product product = productService.getProductById(productId);

        if (product == null) {
            throw new ResourceNotFoundException("Product not found");
        }
        
        if (product.getStockQuantity() < quantity) {
            throw new ResourceNotFoundException("Product quantity unavailable");
        }
		
//		checking the cart exist or not for the user
		Cart cart = cartRepo.findByUserId(userId);
		
		if(cart == null) {
		    cart = new Cart();
		    cart.setUser(user);
		    cart.setTotalPrice(0);
		    cart = cartRepo.save(cart);
		}
		
		CartItems cartItem = cartItemRepo.findByCartIdAndProductId(cart.getId(), productId );
		
//		Checking the Cart Item in Cart Or Not
		if(cartItem==null) {
			cartItem = new CartItems();
			cartItem.setCart(cart);
			cartItem.setProduct(product);
			cartItem.setQuantity(quantity);
			cartItem.setSubtotal(quantity * product.getPrice());
			cartItemRepo.save(cartItem);
			cart.getCartItems().add(cartItem);
		}
		
		else {

            int updatedQuantity = cartItem.getQuantity() + quantity;

            if (updatedQuantity > product.getStockQuantity()) {
                throw new ResourceNotFoundException("Product quantity unavailable");
            }

            cartItem.setQuantity(updatedQuantity);
            cartItem.setSubtotal(updatedQuantity * product.getPrice());

            cartItemRepo.save(cartItem);
        }
		
		
//		Re-Calculating the Total Price
		double total = 0;
		cart = cartRepo.findByUserId(userId);
		for(CartItems item : cart.getCartItems()) {
		    total += item.getSubtotal();
		}
		cart.setTotalPrice(total);

		cartRepo.save(cart);
		
		return cart;
	}

	@Override
	public Cart getCartByUser(Long userId) {
		// TODO Auto-generated method stub
		Cart cart=cartRepo.findByUserId(userId);
		if(cart==null || cart.getCartItems().isEmpty()) {
			throw new ResourceNotFoundException("Cart is empty");
		}
		return cartRepo.findByUserId(userId);
	}

	@Override
	public void deleteProductFromCart(Long userId, Long productId) {
		// TODO Auto-generated method stub
		Cart cart = cartRepo.findByUserId(userId);

        if (cart == null) {
            throw new ResourceNotFoundException("Cart is Empty");
        }

        CartItems cartItem =cartItemRepo.findByCartIdAndProductId(cart.getId(), productId);

        if (cartItem == null) {
            throw new ResourceNotFoundException("Product not found in cart");
        }

        cart.getCartItems().remove(cartItem);
        cartItemRepo.delete(cartItem);

        double total = 0;

        for (CartItems item : cart.getCartItems()) {
            total += item.getSubtotal();
        }

        cart.setTotalPrice(total);
        cartRepo.save(cart);
    }

	@Override
	public void clearCart(Long userId) {
		// TODO Auto-generated method stub
		Cart cart=cartRepo.findByUserId(userId);
		if(cart==null) {
			throw new ResourceNotFoundException("Cart not Found");
		}
		cartRepo.deleteAll();
	}

}
