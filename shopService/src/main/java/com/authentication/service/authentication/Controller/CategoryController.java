package com.authentication.service.authentication.Controller;

import com.authentication.service.authentication.Entity.Category;
import com.authentication.service.authentication.Service.CategoryService;
import com.authentication.service.authentication.exceptionHandling.CategoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class CategoryController {
    @Autowired
    private CategoryService service;

    @GetMapping("/category/getAllCategory")
    public List<Category> getAllCategory(){
        return service.getAllCategory();
    }

    @PostMapping("/category/getCategoryByField")
    public Category getCategoryByField(@RequestBody Category category) throws CategoryException {
        return service.getCategoryByField(category);
    }



    @PostMapping("/category/addCategory")
    public Category addCategory(@RequestBody Category category) throws CategoryException {
        return service.saveCategory(category);
    }

    @PutMapping("/category/updateCategory")
    public Category updateCategory(@RequestBody Category category) throws CategoryException {
        return service.updateCategory(category);
    }

    @DeleteMapping("/category/deleteCategory/{id}")
    public String deleteCategory(@PathVariable("id") int Id) throws CategoryException {
        return service.deleteCategory(Id);
    }

}
