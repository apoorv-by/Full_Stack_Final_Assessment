package com.authentication.service.authentication.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String price;

    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="category_id", referencedColumnName = "id")
    private List<Category> categories = new ArrayList<>();

    public Product() {

    }

    public void addCategory(Category category){
        categories.add(category);
    }

    public void deleteCategory(Category category){
        if(!categories.isEmpty()){
            categories.remove(category);
        }
    }

    public Product(int id, String name, String price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", description='" + description + '\'' +
                ", categories=" + categories +
                '}';
    }
}
