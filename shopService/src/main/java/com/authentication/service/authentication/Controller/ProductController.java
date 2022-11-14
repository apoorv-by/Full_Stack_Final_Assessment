package com.authentication.service.authentication.Controller;

import com.authentication.service.authentication.Entity.Product;
import com.authentication.service.authentication.Service.ProductService;
import com.authentication.service.authentication.exceptionHandling.ProductException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/product/getAllProduct")
    private List<Product> getAllProduct(){
        return service.getAllProduct();
    }

    @PostMapping("/product/getProductByField")
    private Product getProductByField(@RequestBody Product product) throws ProductException {
        return service.getProductByField(product);
    }


    @PostMapping("/product/addProduct")
    private Product addProduct(@RequestBody Product product) throws ProductException {
        return service.addProduct(product);
    }

    @PutMapping("/product/updateProduct")
    private Product updateProduct(@RequestBody Product product) throws ProductException {
        return service.updateProduct(product);
    }

    @DeleteMapping("/product/deleteProduct/{id}")
    public String deleteProduct(@PathVariable("id") int Id) throws ProductException {
        return service.deleteProduct(Id);
    }

    // Assigning
    @GetMapping("/product/{pid}-with-{cid}")
    public Product assignProduct(@PathVariable("pid") int pId, @PathVariable("cid") int cId) throws ProductException {
        return service.assignCategoryToProduct(pId,cId);
    }
}
