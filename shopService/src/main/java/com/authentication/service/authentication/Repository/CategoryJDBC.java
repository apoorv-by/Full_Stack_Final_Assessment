package com.authentication.service.authentication.Repository;

import com.authentication.service.authentication.Entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class CategoryJDBC {
    @Autowired
    JdbcTemplate jdbcTemplate ;
    public Category getCategoryByType(String type){
        String query = "SELECT * FROM Category WHERE TYPE = ?";
        return  jdbcTemplate.queryForObject(query,new BeanPropertyRowMapper<Category>(Category.class),new Object[]{type});
    }
}
