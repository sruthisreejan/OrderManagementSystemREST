package com.example.orders.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "t_order_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    @JoinColumn(name = "order_id")
    private ShopOrder shopOrder;


    @Override
    public String toString() {
        return "OrderItem [orderItemId=" + orderItemId + ", itemId=" + itemId + ", quantity=" + quantity
                + ", shopOrder=" + shopOrder + "]";
    }

}
