package com.dummy.service.dummyService.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Product {

    private int id;

    private String name;

    private String price;

    private String description;

    private List<Category> categories = new ArrayList<>();


    public Product(int id, String name, String price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"name\":\"" + name  +
                "\", \"price\":\"" + price  +
                "\", \"description\":\"" + description  +
                "\", \"categories\":" + categories +
                "}";
    }
}
