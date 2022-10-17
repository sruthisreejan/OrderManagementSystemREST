package com.example.items.service;

import com.example.items.dto.ShopItemDto;
import com.example.items.entity.ShopItem;
import com.example.items.repository.ShopItemRepository;
import com.example.items.util.ShopItemUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShopItemServiceImpl implements ShopItemService {

    @Autowired
    private ShopItemRepository shopItemRepository;

    @Override
    public ShopItemDto saveShopItem(ShopItemDto shopItemDto) {
        if (ObjectUtils.isEmpty(shopItemDto)) {
            return null;
        } else {
            ShopItem shopItem = shopItemRepository.save(ShopItemUtil.copy(shopItemDto));
            return ShopItemUtil.copy(shopItem);
        }
    }

    @Override
    public List<ShopItemDto> saveShopItemsList(List<ShopItemDto> shopItemDtoList) {
        List<ShopItemDto> shopItemDtoSavedList = new ArrayList<>();
        if (ObjectUtils.isEmpty(shopItemDtoList)) {
            return null;
        } else {
            for (ShopItemDto shopItemDto : shopItemDtoList) {
                ShopItem shopItem = shopItemRepository.save(ShopItemUtil.copy(shopItemDto));
                shopItemDtoSavedList.add(ShopItemUtil.copy(shopItem));

            }
        }
        return shopItemDtoSavedList;
    }

    @Override
    public List<ShopItemDto> getAllShopItems() throws Exception {
        Optional<List<ShopItem>> shopItemsList = Optional.of(shopItemRepository.findAll());
        List<ShopItemDto> shopItemDtoList = new ArrayList<>();

        if (shopItemsList.isEmpty()) {
            throw new Exception("ShopItems list is empty!");
        } else {
            for (ShopItem shopItem : shopItemsList.get()) {

                shopItemDtoList.add(ShopItemUtil.copy(shopItem));
            }
            return shopItemDtoList;
        }
    }

    @Override
    public void deleteShopItemById(int id) {
        shopItemRepository.deleteById(id);
    }

    @Override
    public ShopItemDto getShopItemById(int id) throws Exception {
        Optional<ShopItem> shopItem = shopItemRepository.findById(id);
        if(shopItem.isEmpty()){
            throw new Exception("Cannot find item");
        }else {
            return ShopItemUtil.copy(shopItem.get());
        }
    }

    @Override
    public ShopItemDto updateShopItem(ShopItemDto shopItemDto) {

        ShopItem shopItem = shopItemRepository.save(ShopItemUtil.copy(shopItemDto));
        return ShopItemUtil.copy(shopItem);
    }


    @Override
    public void updateItemCount(int id, int quantity) throws Exception {
        Optional<ShopItem> item = shopItemRepository.findById(id);
        if(item.isEmpty()){
            throw new Exception("Cannot find item");
        }else {
            ShopItem shopItem = item.get();
            shopItem.setAvailableCount(shopItem.getAvailableCount() - quantity);
            shopItemRepository.save(shopItem);
        }
    }

}
