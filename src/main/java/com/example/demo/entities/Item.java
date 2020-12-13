package com.example.demo.entities;


import java.util.UUID;

public class Item {
    private UUID id;
    private String name;
    private double price;

    public Item() {

    }

    public Item(String name, double price) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.price = price;
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

    @Override
    public String toString() {
        return "Thing{" +
                ", name=" + name +
                ", price=" + price +
                '}';
    }
}
