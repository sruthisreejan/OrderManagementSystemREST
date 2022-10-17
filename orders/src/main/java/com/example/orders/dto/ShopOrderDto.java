package com.example.orders.dto;

import com.example.orders.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopOrderDto {

    private int orderId;

    @NotEmpty
    private List<OrderItem> itemsList;

    private double totalAmount;

    private double discount;

    private double netAmount;

}
