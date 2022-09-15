package com.example.discount;

import org.springframework.stereotype.Component;

@Component
public interface DiscountService {
	public double getDiscount(double amount);
}
