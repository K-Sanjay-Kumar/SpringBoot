package com.example.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecommerce.model.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
	Orders findByUserId(Long userId);
}
