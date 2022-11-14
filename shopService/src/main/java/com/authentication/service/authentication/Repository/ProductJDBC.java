package com.authentication.service.authentication.Repository;

import com.authentication.service.authentication.Entity.Category;
import com.authentication.service.authentication.Entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductJDBC {
    @Autowired
    JdbcTemplate jdbcTemplate ;
    public Product getProductByName(String name){
        String query = "SELECT * FROM Product WHERE Name = ?";
        return  jdbcTemplate.queryForObject(query,new BeanPropertyRowMapper<Product>(Product.class),new Object[]{name});
    }
    public Product getProductByPrice(String price){
        String query = "SELECT * FROM Product WHERE Price = ?";
        return  jdbcTemplate.queryForObject(query,new BeanPropertyRowMapper<Product>(Product.class),new Object[]{price});
    }
    public Product getProductByDescription(String description){
        String query = "SELECT * FROM Product WHERE Name = ?";
        return  jdbcTemplate.queryForObject(query,new BeanPropertyRowMapper<Product>(Product.class),new Object[]{description});
    }


}
