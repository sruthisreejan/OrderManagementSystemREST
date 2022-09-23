package com.example.orders;

import com.example.orders.controller.ShopOrderController;
import com.example.orders.dto.ShopOrderDto;
import com.example.orders.entity.OrderItem;
import com.example.orders.service.ShopOrderService;
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
public class ShopOrderControllerTest {

    @InjectMocks
    ShopOrderController shopOrderController;

    @Mock
    ShopOrderService shopOrderService;

    @Test
    public void getOrderControllerTest() {
        List<OrderItem> orderItemList = Stream.of(new OrderItem(),
                        new OrderItem(),
                        new OrderItem())
                .collect(Collectors.toList());
        ShopOrderDto shopOrderDto = new ShopOrderDto(1, orderItemList, 1000.00, 100, 900);
        Mockito.when(shopOrderService.getShopOrderById(Mockito.anyInt())).thenReturn(shopOrderDto);
        assertEquals(100, shopOrderController.getShopOrderById(1).getDiscount());
    }

    @Test
    public void getOrdersControllerTest() throws Exception {
        List<OrderItem> orderItemList = Stream.of(new OrderItem(),
                        new OrderItem(),
                        new OrderItem())
                .collect(Collectors.toList());
        List<ShopOrderDto> orderList = Stream.of(new ShopOrderDto(1, orderItemList, 1000.00, 100, 900),
                        new ShopOrderDto(1, orderItemList, 1000.00, 100, 900),
                        new ShopOrderDto(1, orderItemList, 1000.00, 100, 900))
                .collect(Collectors.toList());
        Mockito.when(shopOrderService.getAllShopOrders()).thenReturn(orderList);
        assertEquals(3, shopOrderController.getAllShopOrders().size());
    }

    @Test
    public void deleteOrderControllerTest() {
        shopOrderController.deleteById(1);
        verify(shopOrderService, times(1)).deleteShopOrderById(1);
    }

    @Test
    public void saveOrderControllerTest() throws Exception {
        List<OrderItem> orderItemList = Stream.of(new OrderItem(),
                        new OrderItem(),
                        new OrderItem())
                .collect(Collectors.toList());
        ShopOrderDto shopOrderDto = new ShopOrderDto(1, orderItemList, 1000.00, 100, 900);
        Mockito.when(shopOrderService.calculateTotalBill(shopOrderDto)).thenReturn(shopOrderDto);
        assertEquals(100, shopOrderController.calculateBill(shopOrderDto).getDiscount());
    }
}
