package com.example.orders.dto;

import com.example.orders.entity.ShopOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDto {
	
	private int orderItemId;

	private int itemId;

	private int quantity;

	private ShopOrder shopOrder;

}
