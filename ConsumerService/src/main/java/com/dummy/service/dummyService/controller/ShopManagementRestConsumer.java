package com.dummy.service.dummyService.controller;


import com.dummy.service.dummyService.model.Category;
import com.dummy.service.dummyService.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("shop-service/shop")
public interface ShopManagementRestConsumer {

    // Public API
    @GetMapping("/product/getAllProduct")
    public List<Product> getAllProduct();

    @PostMapping("/product/getProductByField")
    public Product getProductByField(@RequestBody Product product);

    @GetMapping("/category/getAllCategory")
    public List<Category> getAllCategory();

    @PostMapping("/category/getCategoryByField")
    public Category getCategoryByField(@RequestBody Category category);


    // Private API
    @PostMapping("/category/addCategory")
    public Category addCategory(@RequestBody Category category);

    @PutMapping("/category/updateCategory")
    public Category updateCategory(@RequestBody Category category);

    @DeleteMapping("/category/deleteCategory/{id}")
    public String deleteCategory(@PathVariable("id") int Id);

    @PostMapping("/product/addProduct")
    public Product addProduct(@RequestBody Product product);

    @GetMapping("/product/{pid}-with-{cid}")
    public Product assignProduct(@PathVariable("pid") int pId, @PathVariable("cid") int cId);

    @PutMapping("/product/updateProduct")
    public Product updateProduct(@RequestBody Product product);

    @DeleteMapping("/product/deleteProduct/{id}")
    public String deleteProduct(@PathVariable("id") int Id);

}
