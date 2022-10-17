package com.example.orders.service;

import com.example.discount.DiscountService;
import com.example.items.dto.ShopItemDto;
import com.example.items.service.ShopItemService;
import com.example.orders.util.ShopOrderUtil;
import com.example.orders.dto.ShopOrderDto;
import com.example.orders.entity.OrderItem;
import com.example.orders.entity.ShopOrder;
import com.example.orders.repository.OrderItemRepository;
import com.example.orders.repository.ShopOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShopOrderServiceImpl implements ShopOrderService {

    @Autowired
    private ShopOrderRepository shopOrderRepository;

    @Autowired
    private ShopItemService shopItemService;

    @Autowired
    private DiscountService discountService;

    @Autowired
    private OrderItemRepository orderItemRepository;


    @Override
    public List<ShopOrderDto> getAllShopOrders() throws Exception {
        Optional<List<ShopOrder>> shopOrdersList = Optional.of(shopOrderRepository.findAll());
        List<ShopOrderDto> shopOrdersDtoList = new ArrayList<>();

        if (shopOrdersList.isEmpty()) {
            throw new Exception("ShopOrders list is empty!");
        } else {
            for (ShopOrder shopOrder : shopOrdersList.get()) {
                shopOrdersDtoList.add(ShopOrderUtil.copy(shopOrder));
            }

            return shopOrdersDtoList;
        }
    }

    @Override
    public void deleteShopOrderById(int id) {
        shopOrderRepository.deleteById(id);
    }

    @Override
    public ShopOrderDto getShopOrderById(int id) throws Exception {
        Optional<ShopOrder> shopOrder = shopOrderRepository.findById(id);
        if(shopOrder.isEmpty()){
            throw new Exception("Cannot find order");
        }else {
            return ShopOrderUtil.copy(shopOrder.get());
        }
    }


    @Transactional
    public ShopOrderDto calculateTotalBill(ShopOrderDto shopOrderDto) throws Exception {
        List<OrderItem> itemList = shopOrderDto.getItemsList();

        double totalBill = 0;
        int itemId;
        int itemQty;
        double amount;
        int avaQty;
        double totalAmount;
        double discount;
        double amtAfterDisc;
        OrderItem item;
        ShopOrder order;
        for (int i = 0; i < itemList.size(); i++) {
            item = itemList.get(i);
            itemId = item.getItemId();
            itemQty = item.getQuantity();
            Optional<ShopItemDto> itemFromDb = Optional.of(shopItemService.getShopItemById(itemId));
            if (itemFromDb.isEmpty()){
                throw new Exception("Cannot find item");
            }else {
                amount = itemFromDb.get().getPrice();
                avaQty = itemFromDb.get().getAvailableCount();
            }
            if (avaQty < itemQty) {
                throw new Exception("Insufficient stock");
            }
            totalAmount = amount * itemQty;
            totalBill += totalAmount;

            shopItemService.updateItemCount(itemId, itemQty);

        }

        discount = discountService.getDiscount(totalBill);
        amtAfterDisc = totalBill - discount;
        shopOrderDto.setTotalAmount(totalBill);
        shopOrderDto.setDiscount(discount);
        shopOrderDto.setNetAmount(amtAfterDisc);
        order = shopOrderRepository.save(ShopOrderUtil.copy(shopOrderDto));

        for (OrderItem items : itemList) {
            items.setShopOrder(order);
            orderItemRepository.save(items);

        }

        return ShopOrderUtil.copy(order);
    }


}
