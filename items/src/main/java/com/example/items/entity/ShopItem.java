package com.example.items.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_items")
public class ShopItem {
	
	@Override
	public String toString() {
		return "ShopItems [id=" + id + ", description=" + description + ", price=" + price + ", available_count="
				+ available_count + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "available_count")
	private int available_count;

	public ShopItem(int id, String description, double price, int available_count) {
		this.id = id;
		this.description = description;
		this.price = price;
		this.available_count = available_count;
	}

	public ShopItem() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAvailable_count() {
		return available_count;
	}

	public void setAvailable_count(int available_count) {
		this.available_count = available_count;
	}
}
