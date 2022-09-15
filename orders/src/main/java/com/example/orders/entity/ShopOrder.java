package com.example.orders.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "t_orders")
public class ShopOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private int orderId;

	@OneToMany(mappedBy = "shopOrder", cascade = CascadeType.ALL)
	private List<OrderItem> itemsList;

	@Column(name = "total_amount")
	private double totalAmount;

	@Column(name = "discount")
	private double discount;

	@Column(name = "net_amount")
	private double netAmount;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public List<OrderItem> getItemsList() {
		return itemsList;
	}

	public void setItemsList(List<OrderItem> itemsList) {
		this.itemsList = itemsList;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(double netAmount) {
		this.netAmount = netAmount;
	}

	public ShopOrder() {

	}

	public ShopOrder(List<OrderItem> itemsList) {
		this.itemsList = itemsList;
	}

	public ShopOrder(int orderId, List<OrderItem> itemsList, double totalAmount, double discount, double netAmount) {
		super();
		this.orderId = orderId;
		this.itemsList = itemsList;
		this.totalAmount = totalAmount;
		this.discount = discount;
		this.netAmount = netAmount;
	}
	
}
