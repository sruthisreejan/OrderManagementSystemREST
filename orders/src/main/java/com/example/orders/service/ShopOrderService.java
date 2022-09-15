package com.example.orders.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.orders.dto.ShopOrderDto;
import com.example.orders.entity.ShopOrder;


public interface ShopOrderService {
//	public ShopOrderDto saveShopOrder(ShopOrderDto shopOrderDto);
	public List<ShopOrderDto> getAllShopOrders() throws Exception;
	public void deleteShopOrderById(int id);
	public ShopOrderDto getShopOrderById(int id);
	public ShopOrderDto updateShopOrder(ShopOrderDto shopOrderDto);
	public ShopOrderDto calculateTotalBill(ShopOrderDto shopOrderDto) throws Exception;

}
