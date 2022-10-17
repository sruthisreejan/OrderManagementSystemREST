package com.example.items.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopItemDto {

    private int id;

    @NotEmpty
    @Size(min = 3, message = "Description must have at least 3 characters")
    private String description;

    @Range(min = 1, message = "Price must be greater than 0")
    private double price;

    @Range(min = 1, message = "Count must be greater than 0")
    private int availableCount;


}
