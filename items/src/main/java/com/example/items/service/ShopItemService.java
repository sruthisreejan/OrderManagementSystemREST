package com.example.items.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.items.dto.ShopItemDto;
import com.example.items.entity.ShopItem;


public interface ShopItemService {
	public ShopItemDto saveShopItem(ShopItemDto shopItemDto);
	public List<ShopItemDto> getAllShopItems() throws Exception;
	public void deleteShopItemById(int id);
	public ShopItemDto getShopItemById(int id);
	public ShopItemDto updateShopItem(ShopItemDto shopItemDto);
	public void updateItemCount(int id, int quantity);
	public List<ShopItemDto> saveShopItemsList(List<ShopItemDto> shopItemDtoList);

}
