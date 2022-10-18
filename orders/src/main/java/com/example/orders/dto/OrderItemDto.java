package com.example.orders.dto;

import com.example.orders.entity.ShopOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;


import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {

    private int orderItemId;

    @NotNull
    @Range(min = 1, message = "Item id must be greater than 0")
    private int itemId;

    @NotNull
    @Range(min = 1, message = "Quantity must be greater than 0")
    private int quantity;

    private ShopOrder shopOrder;

}
