package com.example.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.model.Cart;
import com.example.ecommerce.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@PostMapping("/addToCart")
	public Cart addProductToCart(@RequestParam Long userId, @RequestParam Long productId, @RequestParam int quantity) {
		return cartService.addProductToCart(userId, productId, quantity);
	}
	
	
	@GetMapping("/{userId}")
	public Cart getCartOfUser(@PathVariable Long userId) {
		return cartService.getCartByUser(userId);
	}
	
	@DeleteMapping("/remove")
	public String deleteProductFromCart(@RequestParam Long userId, @RequestParam Long productId) {
		cartService.deleteProductFromCart(userId, productId);
		return "Product deleted succesfully from the Cart";
	}
	
}
