package com.example.items;

import com.example.items.controller.ShopItemsController;
import com.example.items.dto.ShopItemDto;
import com.example.items.service.ShopItemService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ShopItemControllerTest {

    @InjectMocks
    private ShopItemsController shopItemsController;

    @Mock
    ShopItemService shopItemService;

    @Test
    public void saveItemControllerTest() {
        ShopItemDto shopItemDto = new ShopItemDto(1, "test", 10.00, 20);
        Mockito.when(shopItemService.saveShopItem(shopItemDto)).thenReturn(shopItemDto);
        assertEquals("test", shopItemsController.saveShopItem(shopItemDto).getDescription());

    }

    @Test
    public void updateItemControllerTest() {
        ShopItemDto shopItemDto = new ShopItemDto(1, "test", 10.00, 20);
        Mockito.when(shopItemService.updateShopItem(shopItemDto)).thenReturn(shopItemDto);
        assertEquals("test", shopItemsController.updateShopItem(shopItemDto).getDescription());

    }

    @Test
    public void saveItemsControllerTest() {
        ShopItemDto shopItemDto = new ShopItemDto(1, "test", 10.00, 20);

        List<ShopItemDto> itemList = Stream.of(new ShopItemDto(1, "test1", 10.00, 40),
                        new ShopItemDto(2, "test2", 20.00, 50),
                        new ShopItemDto(3, "test3", 30.00, 60))
                .collect(Collectors.toList());
        Mockito.when(shopItemService.saveShopItemsList(itemList)).thenReturn(itemList);
        assertEquals(3, shopItemsController.saveShopItems(itemList).size());

    }

    @Test
    public void getItemsControllerTest() throws Exception {
        ShopItemDto shopItemDto = new ShopItemDto(1, "test", 10.00, 20);

        List<ShopItemDto> itemList = Stream.of(new ShopItemDto(1, "test1", 10.00, 40),
                        new ShopItemDto(2, "test2", 20.00, 50),
                        new ShopItemDto(3, "test3", 30.00, 60))
                .collect(Collectors.toList());
        Mockito.when(shopItemService.getAllShopItems()).thenReturn(itemList);
        assertEquals(3, shopItemsController.getAllShopItems().size());
    }

    @Test
    public void getItemControllerTest() throws Exception {
        ShopItemDto shopItemDto = new ShopItemDto(1, "test", 10.00, 20);
        Mockito.when(shopItemService.getShopItemById(Mockito.anyInt())).thenReturn(shopItemDto);
        assertEquals("test", shopItemsController.getShopItemById(1).getDescription());
    }

    @Test
    public void deleteItemControllerTest() {
        shopItemsController.deleteById(1);
        verify(shopItemService, times(1)).deleteShopItemById(1);
    }
}
