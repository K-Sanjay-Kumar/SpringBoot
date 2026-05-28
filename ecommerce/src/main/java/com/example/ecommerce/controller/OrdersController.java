package com.example.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.model.Orders;
import com.example.ecommerce.service.OrdersService;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {
	
	@Autowired
	private OrdersService orderService;
	
	@PostMapping("/{userId}")
	public Orders placeOrder(@PathVariable Long userId) {
		return orderService.placeOrder(userId);
	}
	
	@GetMapping("/{userId}")
	public List<Orders> getOrdersByUser(@PathVariable Long userId){
		return orderService.getOrdersByUser(userId);
	}
	
}
