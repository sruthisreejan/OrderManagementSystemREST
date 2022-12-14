package com.example.items.controller;

import com.example.items.dto.ShopItemDto;
import com.example.items.service.ShopItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ShopItemsController {

    @Autowired
    private ShopItemService shopItemService;

    @PostMapping("/save")
    public ShopItemDto saveShopItem(@Valid @RequestBody ShopItemDto shopItemDto) {
        return shopItemService.saveShopItem(shopItemDto);
    }

    @PostMapping("/saveitemslist")
    public List<ShopItemDto> saveShopItems(@Valid @RequestBody List<ShopItemDto> shopItemDtoList) {
        return shopItemService.saveShopItemsList(shopItemDtoList);
    }

    @GetMapping("/all")
    public List<ShopItemDto> getAllShopItems() {
        try {
            return shopItemService.getAllShopItems();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable int id) {
        shopItemService.deleteShopItemById(id);
    }

    @GetMapping("/get/{id}")
    public ShopItemDto getShopItemById(@PathVariable int id) throws Exception {
        return shopItemService.getShopItemById(id);
    }

    @PutMapping("/update")
    public ShopItemDto updateShopItem(@Valid @RequestBody ShopItemDto shopItemDto) {
        return shopItemService.updateShopItem(shopItemDto);
    }
}
