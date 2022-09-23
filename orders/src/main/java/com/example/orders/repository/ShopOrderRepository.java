package com.example.orders.repository;

import com.example.orders.entity.ShopOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopOrderRepository extends JpaRepository<ShopOrder, Integer> {

}
