package com.example.orders.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

}
