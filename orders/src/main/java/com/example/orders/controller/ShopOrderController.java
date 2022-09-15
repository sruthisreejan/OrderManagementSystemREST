package com.example.orders.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.orders.dto.ShopOrderDto;
import com.example.orders.entity.ShopOrder;
import com.example.orders.service.ShopOrderService;

@RestController
@RequestMapping("/orders")
public class ShopOrderController {

	@Autowired
	private ShopOrderService shopOrderService;

//	@PostMapping("/save")
//	public ShopOrder saveShopOrder(@RequestBody ShopOrder shopOrder) {
//		return shopOrderService.saveShopOrder(shopOrder);
//	}

	@GetMapping("/all")
	public List<ShopOrderDto> getAllShopOrders() {
		try {
			return shopOrderService.getAllShopOrders();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@DeleteMapping("/delete/{id}")
	public void deleteById(@PathVariable int id) {
		shopOrderService.deleteShopOrderById(id);
	}

	@GetMapping("/get/{id}")
	public ShopOrderDto getShopOrderById(@PathVariable int id) {
		return shopOrderService.getShopOrderById(id);
	}

	@PutMapping("/save")
	public ShopOrderDto updateShopOrder(@RequestBody ShopOrderDto shopOrderDto) {
		return shopOrderService.updateShopOrder(shopOrderDto);
	}

	@PostMapping("/calculate")
	public ShopOrderDto calculateBill(@RequestBody ShopOrderDto shopOrderDto) throws Exception {
		return shopOrderService.calculateTotalBill(shopOrderDto);
	}

	public ShopOrderController() {
	}

}
