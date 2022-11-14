package com.dummy.service.dummyService.model;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Category {

    private int id;

    private String type;

    public Category(int id, String type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"type\":\"" + type + "\"" +
                '}';
    }
}
