package com.example.items.service;

import com.example.items.dto.ShopItemDto;

import java.util.List;


public interface ShopItemService {
    public ShopItemDto saveShopItem(ShopItemDto shopItemDto);

    public List<ShopItemDto> getAllShopItems() throws Exception;

    public void deleteShopItemById(int id);

    public ShopItemDto getShopItemById(int id) throws Exception;

    public ShopItemDto updateShopItem(ShopItemDto shopItemDto);

    public void updateItemCount(int id, int quantity) throws Exception;

    public List<ShopItemDto> saveShopItemsList(List<ShopItemDto> shopItemDtoList);

}
