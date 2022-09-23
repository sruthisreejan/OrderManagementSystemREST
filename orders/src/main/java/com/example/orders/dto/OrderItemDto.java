package com.example.orders.dto;

import com.example.orders.entity.ShopOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {

    private int orderItemId;

    private int itemId;

    private int quantity;

    private ShopOrder shopOrder;

}
