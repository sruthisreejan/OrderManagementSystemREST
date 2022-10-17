package com.example.orders.dto;

import com.example.orders.entity.ShopOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {

    private int orderItemId;

    @NotEmpty
    private int itemId;

    @NotEmpty
    private int quantity;

    private ShopOrder shopOrder;

}
