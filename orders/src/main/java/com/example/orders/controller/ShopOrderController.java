package com.example.orders.controller;

import com.example.orders.dto.ShopOrderDto;
import com.example.orders.service.ShopOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class ShopOrderController {

    @Autowired
    private ShopOrderService shopOrderService;

    public ShopOrderController() {
    }

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

    @PostMapping("/save")
    public ShopOrderDto calculateBill(@RequestBody ShopOrderDto shopOrderDto) throws Exception {
        return shopOrderService.calculateTotalBill(shopOrderDto);
    }

}
