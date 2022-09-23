package com.example.orders.dto;

import com.example.orders.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopOrderDto {

    private int orderId;

    private List<OrderItem> itemsList;

    private double totalAmount;

    private double discount;

    private double netAmount;

}
