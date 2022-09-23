package com.example.orders;

import com.example.items.dto.ShopItemDto;
import com.example.items.service.ShopItemServiceImpl;
import com.example.normaldiscount.NormalDiscountImpl;
import com.example.orders.dto.ShopOrderDto;
import com.example.orders.entity.OrderItem;
import com.example.orders.entity.ShopOrder;
import com.example.orders.repository.OrderItemRepository;
import com.example.orders.repository.ShopOrderRepository;
import com.example.orders.service.ShopOrderServiceImpl;
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
public class ShopOrderServiceTest {

    @InjectMocks
    ShopOrderServiceImpl shopOrderService;

    @Mock
    ShopOrderRepository repo;

    @Mock
    OrderItemRepository orderItemRepository;

    @Mock
    NormalDiscountImpl discountService;

    @Mock
    ShopItemServiceImpl shopItemService;

    @Test
    public void getOrderServiceTest() {
        List<OrderItem> orderItemList = Stream.of(new OrderItem(),
                        new OrderItem(),
                        new OrderItem())
                .collect(Collectors.toList());
        ShopOrder shopOrder = new ShopOrder(1, orderItemList, 1000.00, 100, 900);
        Mockito.when(repo.findById(Mockito.anyInt())).thenReturn(Optional.of(shopOrder));
        assertEquals(100, shopOrderService.getShopOrderById(1).getDiscount());

    }

    @Test
    public void getOrdersServiceTest() throws Exception {
        List<OrderItem> orderItemList = Stream.of(new OrderItem(),
                        new OrderItem(),
                        new OrderItem())
                .collect(Collectors.toList());
        List<ShopOrder> orderList = Stream.of(new ShopOrder(1, orderItemList, 1000.00, 100, 900),
                        new ShopOrder(1, orderItemList, 1000.00, 100, 900),
                        new ShopOrder(1, orderItemList, 1000.00, 100, 900))
                .collect(Collectors.toList());
        Mockito.when(repo.findAll()).thenReturn(orderList);
        assertEquals(3, shopOrderService.getAllShopOrders().size());

    }

    @Test
    public void saveOrderServiceTest() throws Exception {
        List<OrderItem> orderItemList = Stream.of(new OrderItem(),
                        new OrderItem(),
                        new OrderItem())
                .collect(Collectors.toList());
        ShopOrder shopOrder = new ShopOrder(1, orderItemList, 1000.00, 100, 900);
        ShopOrderDto shopOrderDto = new ShopOrderDto(1, orderItemList, 1000.00, 100, 900);
        OrderItem orderItem = new OrderItem();
        Mockito.when(repo.save(Mockito.any())).thenReturn(shopOrder);
        Mockito.when(orderItemRepository.save(Mockito.any())).thenReturn(orderItem);
        Mockito.when(discountService.getDiscount(Mockito.anyDouble())).thenReturn(100.00);
        Mockito.when(shopItemService.getShopItemById(Mockito.anyInt())).thenReturn(new ShopItemDto());

        assertEquals(100, shopOrderService.calculateTotalBill(shopOrderDto).getDiscount());
    }

    @Test
    public void deleteOrderServiceTest() {
        shopOrderService.deleteShopOrderById(1);
        verify(repo, times(1)).deleteById(1);
    }
}
