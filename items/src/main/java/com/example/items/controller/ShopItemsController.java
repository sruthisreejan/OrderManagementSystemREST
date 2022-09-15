package com.example.items.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.items.service.ShopItemService;
import com.example.items.dto.ShopItemDto;
import com.example.items.entity.ShopItem;

@RestController
@RequestMapping("/items")
public class ShopItemsController {

	@Autowired
	private ShopItemService shopItemService;
	
	@PostMapping("/save")
	public ShopItemDto saveShopItem(@RequestBody ShopItemDto shopItemDto) {
		return shopItemService.saveShopItem(shopItemDto);
	}
	
	@PostMapping("/saveitemslist")
	public List<ShopItemDto> saveShopItems(@RequestBody List<ShopItemDto> shopItemDtoList) {
		return shopItemService.saveShopItemsList(shopItemDtoList);
	}
	
	@GetMapping("/all")
	public List<ShopItemDto> getAllShopItems(){
		try {
			return shopItemService.getAllShopItems();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;		
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteById(@PathVariable int id) {
		shopItemService.deleteShopItemById(id);
	}
	
	@GetMapping("/get/{id}")
	public ShopItemDto getShopItemById(@PathVariable int id){
		return shopItemService.getShopItemById(id);
	}
	
	@PutMapping("/save")
	public ShopItemDto updateShopItem(@RequestBody ShopItemDto shopItemDto) {
		return shopItemService.updateShopItem(shopItemDto);
	}
}
