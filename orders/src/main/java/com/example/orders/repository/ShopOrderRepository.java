package com.example.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.orders.entity.ShopOrder;

public interface ShopOrderRepository extends JpaRepository<ShopOrder, Integer> {

}
