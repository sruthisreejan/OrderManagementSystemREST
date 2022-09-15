package com.example.orders.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.discount.DiscountService;
import com.example.items.service.ShopItemService;
import com.example.orders.dto.ShopOrderDto;
import com.example.orders.entity.OrderItem;
import com.example.orders.entity.ShopOrder;
import com.example.orders.repository.OrderItemRepository;
import com.example.orders.repository.ShopOrderRepository;

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

//	@Override
//	public ShopOrderDto saveShopOrder(ShopOrder shopOrder) {
//		if (ObjectUtils.isEmpty(shopOrder)) {
//			return null;
//		} else {
//			return shopOrderRepository.save(shopOrder);
//		}
//	}

	@Override
	public List<ShopOrderDto> getAllShopOrders() throws Exception {
		List<ShopOrder> shopOrdersList = shopOrderRepository.findAll();
		List<ShopOrderDto> shopOrdersDtoList = new ArrayList<>();

		if (shopOrdersList.isEmpty()) {
			throw new Exception("ShopOrders list is empty!");
		} else {
			for (ShopOrder shopOrder : shopOrdersList) {
				shopOrdersDtoList.add(copy(shopOrder));
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
		return copy(shopOrderRepository.findById(id).get());
	}

	@Override
	public ShopOrderDto updateShopOrder(ShopOrderDto shopOrderDto) {
		ShopOrder shopOrder = shopOrderRepository.save(copy(shopOrderDto));
		return copy(shopOrder);

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
		order = shopOrderRepository.save(copy(shopOrderDto));

		for (OrderItem items : itemList) {
			items.setShopOrder(order);
			orderItemRepository.save(items);

		}

		return copy(order);
	}

	private ShopOrderDto copy(ShopOrder shopOrder) {
		ShopOrderDto shopOrderDto = new ShopOrderDto();
		shopOrderDto.setOrderId(shopOrder.getOrderId());
		shopOrderDto.setItemsList(shopOrder.getItemsList());
		shopOrderDto.setTotalAmount(shopOrder.getTotalAmount());
		shopOrderDto.setDiscount(shopOrder.getDiscount());
		shopOrderDto.setNetAmount(shopOrder.getNetAmount());

		return shopOrderDto;

	}

	private ShopOrder copy(ShopOrderDto shopOrderDto) {
		ShopOrder shopOrder = new ShopOrder();
		shopOrder.setOrderId(shopOrderDto.getOrderId());
		shopOrder.setItemsList(shopOrderDto.getItemsList());
		shopOrder.setTotalAmount(shopOrderDto.getTotalAmount());
		shopOrder.setDiscount(shopOrderDto.getDiscount());
		shopOrder.setNetAmount(shopOrderDto.getNetAmount());

		return shopOrder;

	}

}
