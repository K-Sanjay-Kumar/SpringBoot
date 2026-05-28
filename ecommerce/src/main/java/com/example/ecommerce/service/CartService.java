package com.example.ecommerce.service;

import com.example.ecommerce.model.Cart;

public interface CartService {
	Cart addProductToCart(Long userId, Long productId, int quantity);
	Cart getCartByUser(Long userId);
	void deleteProductFromCart(Long userId, Long productId);
	void clearCart(Long userId);
}
