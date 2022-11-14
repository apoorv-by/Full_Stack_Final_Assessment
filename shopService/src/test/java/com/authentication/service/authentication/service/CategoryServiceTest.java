package com.authentication.service.authentication.service;

import com.authentication.service.authentication.Entity.Category;
import com.authentication.service.authentication.Repository.CategoryJDBC;
import com.authentication.service.authentication.Repository.CatergoryRepository;
import com.authentication.service.authentication.Service.CategoryService;
import com.authentication.service.authentication.exceptionHandling.CategoryException;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class CategoryServiceTest {

    @Mock
    private CatergoryRepository repository;

    @Mock
    private CategoryJDBC jdbc;

    @InjectMocks
    private CategoryService service;

    @Before // Before Each Test Case, ready the mocked data
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public  void getAllCategory(){
        List<Category> categoryList = new ArrayList<Category>();

        categoryList.add(new Category(1,"cloth"));
        categoryList.add(new Category(2,"electric"));
        categoryList.add(new Category(3,"food"));



        when(repository.findAll()).thenReturn(categoryList);

        List<Category> allCategory = service.getAllCategory();

        assertEquals(3,allCategory.size());
    }

    @Test
    public  void getCategoryByField() throws CategoryException {
        Category category = new Category(3, "cloth");


        when(jdbc.getCategoryByType("food")).thenReturn(category);

        Category category1 = service.getCategoryByField(new Category(0,"food"));

        assertEquals(category1.getId(),category.getId());
        assertEquals(category1.getType(),category.getType());

    }

    @Test
    public  void saveCategory() throws CategoryException {
        List<Category> categoryList = new ArrayList<Category>();

        categoryList.add(new Category(1,"cloth"));
        categoryList.add(new Category(2,"electric"));
        categoryList.add(new Category(3,"food"));

        Category category = new Category(4, "xyz");
        categoryList.add(category);

        when(repository.save(category)).thenReturn(category);

        when(jdbc.getCategoryByType(category.getType())).thenReturn(category);

        Category category1 = service.saveCategory(category);

        assertEquals(category1.getId(),category.getId());
        assertEquals(category1.getType(),category.getType());
    }

    @Test
    public  void deleteCategory() throws CategoryException {
        Category category = new Category(1, "food");
        service.deleteCategory(1);
        verify(repository, times(1)).deleteById(category.getId());
    }

}
