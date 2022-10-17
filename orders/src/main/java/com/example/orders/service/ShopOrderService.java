package com.example.orders.service;

import com.example.orders.dto.ShopOrderDto;

import java.util.List;


public interface ShopOrderService {

    public List<ShopOrderDto> getAllShopOrders() throws Exception;

    public void deleteShopOrderById(int id);

    public ShopOrderDto getShopOrderById(int id) throws Exception;

    public ShopOrderDto calculateTotalBill(ShopOrderDto shopOrderDto) throws Exception;

}
