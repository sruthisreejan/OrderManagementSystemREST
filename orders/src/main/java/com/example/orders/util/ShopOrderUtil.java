package com.example.orders.util;

import com.example.orders.dto.ShopOrderDto;
import com.example.orders.entity.ShopOrder;

public class ShopOrderUtil {
    public static ShopOrderDto copy(ShopOrder shopOrder) {
        ShopOrderDto shopOrderDto = new ShopOrderDto();
        shopOrderDto.setOrderId(shopOrder.getOrderId());
        shopOrderDto.setItemsList(shopOrder.getItemsList());
        shopOrderDto.setTotalAmount(shopOrder.getTotalAmount());
        shopOrderDto.setDiscount(shopOrder.getDiscount());
        shopOrderDto.setNetAmount(shopOrder.getNetAmount());

        return shopOrderDto;

    }

    public static ShopOrder copy(ShopOrderDto shopOrderDto) {
        ShopOrder shopOrder = new ShopOrder();
        shopOrder.setOrderId(shopOrderDto.getOrderId());
        shopOrder.setItemsList(shopOrderDto.getItemsList());
        shopOrder.setTotalAmount(shopOrderDto.getTotalAmount());
        shopOrder.setDiscount(shopOrderDto.getDiscount());
        shopOrder.setNetAmount(shopOrderDto.getNetAmount());

        return shopOrder;

    }
}
