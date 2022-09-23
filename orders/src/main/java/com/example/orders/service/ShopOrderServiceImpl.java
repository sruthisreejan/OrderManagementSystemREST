package com.example.orders.service;

import com.example.discount.DiscountService;
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
        List<ShopOrder> shopOrdersList = shopOrderRepository.findAll();
        List<ShopOrderDto> shopOrdersDtoList = new ArrayList<>();

        if (shopOrdersList.isEmpty()) {
            throw new Exception("ShopOrders list is empty!");
        } else {
            for (ShopOrder shopOrder : shopOrdersList) {
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
    public ShopOrderDto getShopOrderById(int id) {
        return ShopOrderUtil.copy(shopOrderRepository.findById(id).get());
    }


    @Transactional
    public ShopOrderDto calculateTotalBill(ShopOrderDto shopOrderDto) throws Exception {
        List<OrderItem> itemList = shopOrderDto.getItemsList();

        double totalBill = 0;
        int item_id;
        int item_qty;
        double amount;
        int ava_qty;
        double totalAmount;
        double discount;
        double amtAfterDisc;
        OrderItem item;
        ShopOrder order;
        for (int i = 0; i < itemList.size(); i++) {
            item = itemList.get(i);
            item_id = item.getItemId();
            item_qty = item.getQuantity();
            amount = shopItemService.getShopItemById(item_id).getPrice();
            ava_qty = shopItemService.getShopItemById(item_id).getAvailable_count();
            if (ava_qty < item_qty) {
                throw new Exception("Insufficient stock");
            }
            totalAmount = amount * item_qty;
            totalBill += totalAmount;

            shopItemService.updateItemCount(item_id, item_qty);

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
