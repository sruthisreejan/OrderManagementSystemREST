package com.example.orders.dto;

import java.util.List;

import com.example.orders.entity.OrderItem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopOrderDto {

	private int orderId;

	private List<OrderItem> itemsList;

	private double totalAmount;

	private double discount;

	private double netAmount;

}
