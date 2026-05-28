package com.example.ecommerce.service;

import java.util.List;

import com.example.ecommerce.model.Orders;

public interface OrdersService {
	Orders placeOrder(Long userId);
	List<Orders> getOrdersByUser(Long userId);
}
