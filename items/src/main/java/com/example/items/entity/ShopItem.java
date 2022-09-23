package com.example.items.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
}
