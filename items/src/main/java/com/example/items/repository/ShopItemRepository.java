package com.example.items.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.items.entity.ShopItem;


@Repository
public interface ShopItemRepository extends JpaRepository<ShopItem, Integer>{

}
