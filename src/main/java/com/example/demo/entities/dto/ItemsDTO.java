package com.example.demo.entities.dto;

import com.example.demo.entities.Item;

import java.util.List;

public class ItemsDTO {

    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "ItemsDTO{" +
                "items=" + items +
                '}';
    }
}
