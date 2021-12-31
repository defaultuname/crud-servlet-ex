package com.product.model;

import java.math.BigDecimal;

public class Smartphone {

    private int id;
    private String model;
    private BigDecimal price;

    public Smartphone() {
    }

    public Smartphone(String model, BigDecimal price) {
        this.model = model;
        this.price = price;
    }

    public Smartphone(int id, String model, BigDecimal price) {
        this.id = id;
        this.model = model;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
    }

    @Override
    public String toString() {
        return "Smartphone{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", price=" + price +
                '}';
    }
}
