package com.example.demo.entities.dto;


import com.example.demo.entities.Order;

import java.util.List;

public class OrdersDTO {
    private List<Order> orders;

    public OrdersDTO() {
    }

    public OrdersDTO(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
