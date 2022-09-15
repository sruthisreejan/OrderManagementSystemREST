package com.example.orders.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "t_order_items")
public class OrderItem {
	@Id
	@Column(name = "order_item_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderItemId;
	
	@Column(name = "item_id")
	private int itemId;

	@Column(name = "quantity")
	private int quantity;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="order_id")
	private ShopOrder shopOrder;

	public int getOrderItemId() {
		return orderItemId;
	}

	public OrderItem() {
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public ShopOrder getShopOrder() {
		return shopOrder;
	}

	public void setShopOrder(ShopOrder shopOrder) {
		this.shopOrder = shopOrder;
	}

	@Override
	public String toString() {
		return "OrderItem [orderItemId=" + orderItemId + ", itemId=" + itemId + ", quantity=" + quantity
				+ ", shopOrder=" + shopOrder + "]";
	}

	public OrderItem(int orderItemId, int itemId, int quantity, ShopOrder shopOrder) {
		super();
		this.orderItemId = orderItemId;
		this.itemId = itemId;
		this.quantity = quantity;
		this.shopOrder = shopOrder;
	}

	

	

}
