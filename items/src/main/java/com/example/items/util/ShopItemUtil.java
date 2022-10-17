package com.example.items.util;

import com.example.items.dto.ShopItemDto;
import com.example.items.entity.ShopItem;

public class ShopItemUtil {
    public static ShopItem copy(ShopItemDto shopItemDto) {
        ShopItem shopItem = new ShopItem();
        shopItem.setId(shopItemDto.getId());
        shopItem.setDescription(shopItemDto.getDescription());
        shopItem.setPrice(shopItemDto.getPrice());
        shopItem.setAvailableCount(shopItemDto.getAvailableCount());
        return shopItem;
    }

    public static ShopItemDto copy(ShopItem shopItem) {
        ShopItemDto shopItemDto = new ShopItemDto();
        shopItemDto.setId(shopItem.getId());
        shopItemDto.setDescription(shopItem.getDescription());
        shopItemDto.setPrice(shopItem.getPrice());
        shopItemDto.setAvailableCount(shopItem.getAvailableCount());
        return shopItemDto;
    }

    private ShopItemUtil() {
    }
}
