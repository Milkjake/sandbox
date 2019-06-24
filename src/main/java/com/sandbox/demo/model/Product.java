package com.sandbox.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private double price;
    private Timestamp bought;
    private Timestamp sold;

    public Product() {
    }

    public Product(String name, double price, Timestamp bought, Timestamp sold) {
        this.name = name;
        this.price = price;
        this.bought = bought;
        this.sold = sold;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Timestamp getBought() {
        return bought;
    }

    public void setBought(Timestamp bought) {
        this.bought = bought;
    }

    public Timestamp getSold() {
        return sold;
    }

    public void setSold(Timestamp sold) {
        this.sold = sold;
    }
}