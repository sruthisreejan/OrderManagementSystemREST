package com.example.items;


import com.example.items.dto.ShopItemDto;
import com.example.items.entity.ShopItem;
import com.example.items.repository.ShopItemRepository;
import com.example.items.service.ShopItemServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ShopItemServiceTest {

    @InjectMocks
    ShopItemServiceImpl shopItemService;


    @Mock
    ShopItemRepository repo;

    @Test
    public void saveItemServiceTest() {
        ShopItemDto shopItemDto = new ShopItemDto(1, "test", 10.00, 20);
        ShopItem shopItem = new ShopItem(1, "test", 10.00, 20);
        Mockito.when(repo.save(Mockito.any())).thenReturn(shopItem);
        assertEquals(1, shopItemService.saveShopItem(shopItemDto).getId());
    }

    @Test
    public void saveItemsServiceTest() {
        ShopItem shopItem = new ShopItem(1, "test1", 10.00, 40);

        List<ShopItemDto> itemDtoList = Stream.of(new ShopItemDto(1, "test1", 10.00, 40),
                        new ShopItemDto(2, "test2", 20.00, 50),
                        new ShopItemDto(3, "test3", 30.00, 60))
                .collect(Collectors.toList());
        Mockito.when(repo.save(Mockito.any())).thenReturn(shopItem);
        assertEquals(3, shopItemService.saveShopItemsList(itemDtoList).size());
    }

    @Test
    public void updateItemServiceTest() {
        ShopItemDto shopItemDto = new ShopItemDto(1, "test", 10.00, 20);
        ShopItem shopItem = new ShopItem(1, "test", 10.00, 20);
        Mockito.when(repo.save(Mockito.any())).thenReturn(shopItem);
        assertEquals(1, shopItemService.updateShopItem(shopItemDto).getId());
    }

    @Test
    public void getItemsServiceTest() throws Exception {
        List<ShopItem> itemList = Stream.of(new ShopItem(1, "test1", 10.00, 40),
                        new ShopItem(2, "test2", 20.00, 50),
                        new ShopItem(3, "test3", 30.00, 60))
                .collect(Collectors.toList());
        Mockito.when(repo.findAll()).thenReturn(itemList);
        assertEquals(3, shopItemService.getAllShopItems().size());
    }

    @Test
    public void getItemServiceTest() {
        ShopItemDto shopItemDto = new ShopItemDto(1, "test", 10.00, 20);
        ShopItem shopItem = new ShopItem(1, "test", 10.00, 20);
        Mockito.when(repo.findById(Mockito.anyInt())).thenReturn(Optional.of(shopItem));
        assertEquals("test", shopItemService.getShopItemById(1).getDescription());

    }

    @Test
    public void deleteItemServiceTest() {
        shopItemService.deleteShopItemById(1);
        verify(repo, times(1)).deleteById(1);
    }
}