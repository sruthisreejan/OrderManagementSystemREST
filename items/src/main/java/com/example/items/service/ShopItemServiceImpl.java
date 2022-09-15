package com.example.items.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.example.items.dto.ShopItemDto;
import com.example.items.entity.ShopItem;
import com.example.items.repository.ShopItemRepository;

@Service
public class ShopItemServiceImpl implements ShopItemService {

	@Autowired
	private ShopItemRepository shopItemRepository;

	@Override
	public ShopItemDto saveShopItem(ShopItemDto shopItemDto) {
		if (ObjectUtils.isEmpty(shopItemDto)) {
			return null;
		} else {
			ShopItem shopItem = shopItemRepository.save(copy(shopItemDto));
			return copy(shopItem);
		}
	}
	
	@Override
	public List<ShopItemDto> saveShopItemsList(List<ShopItemDto> shopItemDtoList){
		List<ShopItemDto> shopItemDtoSavedList = new ArrayList<>();
		if (ObjectUtils.isEmpty(shopItemDtoList)) {
			return null;
		} else {
			for (ShopItemDto shopItemDto : shopItemDtoList) {
				ShopItem shopItem =shopItemRepository.save(copy(shopItemDto));
				shopItemDtoSavedList.add(copy(shopItem));
				
			}
		}
		return shopItemDtoSavedList;
	}

	@Override
	public List<ShopItemDto> getAllShopItems() throws Exception {
		List<ShopItem> shopItemsList = shopItemRepository.findAll();
		List<ShopItemDto> shopItemDtoList = new ArrayList<>();

		if (shopItemsList.isEmpty()) {
			throw new Exception("ShopItems list is empty!");
		} else {
			for (ShopItem shopItem : shopItemsList) {

				shopItemDtoList.add(copy(shopItem));
			}
			return shopItemDtoList;
		}
	}

	@Override
	public void deleteShopItemById(int id) {
		shopItemRepository.deleteById(id);
	}

	@Override
	public ShopItemDto getShopItemById(int id) {
		return copy(shopItemRepository.findById(id).get());
	}

	@Override
	public ShopItemDto updateShopItem(ShopItemDto shopItemDto) {

		ShopItem shopItem = shopItemRepository.save(copy(shopItemDto));
		return copy(shopItem);
	}

	private ShopItem copy(ShopItemDto shopItemDto) {
		ShopItem shopItem = new ShopItem();
		shopItem.setId(shopItemDto.getId());
		shopItem.setDescription(shopItemDto.getDescription());
		shopItem.setPrice(shopItemDto.getPrice());
		shopItem.setAvailable_count(shopItemDto.getAvailable_count());
		return shopItem;
	}

	private ShopItemDto copy(ShopItem shopItem) {
		ShopItemDto shopItemDto = new ShopItemDto();
		shopItemDto.setId(shopItem.getId());
		shopItemDto.setDescription(shopItem.getDescription());
		shopItemDto.setPrice(shopItem.getPrice());
		shopItemDto.setAvailable_count(shopItem.getAvailable_count());
		return shopItemDto;
	}

	@Override
	public void updateItemCount(int id, int quantity) {

		ShopItem shopItem = shopItemRepository.findById(id).get();
		shopItem.setAvailable_count(shopItem.getAvailable_count() - quantity);
		shopItemRepository.save(shopItem);
	}
	
}
