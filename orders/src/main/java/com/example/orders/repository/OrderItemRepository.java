package com.example.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.orders.entity.OrderItem;
		
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

}
