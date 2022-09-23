package com.example.festivaldiscount;

import org.springframework.stereotype.Component;

import com.example.discount.DiscountService;

@Component
public class FestivalDiscountImpl implements DiscountService{
	@Override
	public double getDiscount(double amount) {
		double discountPercent=0.2;
		double discountPrice=amount*discountPercent;
		return discountPrice;
	}
}