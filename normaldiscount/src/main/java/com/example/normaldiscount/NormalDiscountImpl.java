package com.example.normaldiscount;

import org.springframework.stereotype.Component;

import com.example.discount.DiscountService;

@Component
public class NormalDiscountImpl implements DiscountService {
    @Override
    public double getDiscount(double amount) {
        double discountPercent = 0.1;
        double discountPrice = amount * discountPercent;
        return discountPrice;
    }
}
