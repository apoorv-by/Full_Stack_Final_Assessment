package com.authentication.service.authentication.Service;

import com.authentication.service.authentication.Entity.Category;
import com.authentication.service.authentication.Entity.Product;
import com.authentication.service.authentication.Repository.CatergoryRepository;
import com.authentication.service.authentication.Repository.ProductJDBC;
import com.authentication.service.authentication.Repository.ProductRepository;
import com.authentication.service.authentication.exceptionHandling.CategoryException;
import com.authentication.service.authentication.exceptionHandling.ProductException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductJDBC productJDBC;

    @Autowired
    private CatergoryRepository catergoryRepository;

    public List<Product> getAllProduct(){
        return repository.findAll();
    }

    public Product getProductByField(Product product) throws ProductException {
        try{
            if(product.getId()!=0){
                return repository.findById(product.getId()).get();
            }

            if(product.getName()!=null){
                return productJDBC.getProductByName(product.getName());
            }
            if(product.getPrice()!=null){
                return productJDBC.getProductByPrice(product.getPrice());
            }
            if(product.getDescription()!=null){
                return productJDBC.getProductByDescription(product.getDescription());
            }
            return null;
        }
        catch (Exception e){
            throw new ProductException("NOT FOUND!").setDefaultException(HttpStatus.NOT_FOUND);
        }

    }

    public Product addProduct(Product product) throws ProductException {
        if(product.getName()==null) throw new ProductException("Name must not be null!").setDefaultException(HttpStatus.NOT_ACCEPTABLE);
        if(product.getDescription()==null) throw new ProductException("Description must not be null!").setDefaultException(HttpStatus.NOT_ACCEPTABLE);
        if(product.getPrice()==null) throw new ProductException("Price must not be null!").setDefaultException(HttpStatus.NOT_ACCEPTABLE);
        return repository.save(product);
    }

    public Product updateProduct(Product product) throws ProductException {
        try{

            Optional<Product> product_db = repository.findById(product.getId());
            if(!product_db.isPresent())
                throw new ProductException("Wrong Id!").setDefaultException(HttpStatus.NOT_ACCEPTABLE);
            product.setCategories(product_db.get().getCategories());
            return repository.save(product);
        }
        // Add Error
        catch (Exception e){
            throw new ProductException("Wrong Id!").setDefaultException(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    public String deleteProduct(int Id) throws ProductException {
        try{
            repository.findById(Id);
            repository.deleteById(Id);
            return "{\"message\":\"Successfully Deleted!\"}";
        }
        catch (Exception e){
            throw new ProductException("Wrong Id!").setDefaultException(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    public Product assignCategoryToProduct(int pID, int cID) throws ProductException {
        try{
            Category category = catergoryRepository.findById(cID).get();
            Product product = repository.findById(pID).get();
            product.addCategory(category);
            repository.save(product);
            return repository.findById(pID).get();
        }
        catch (Exception e){
            throw new ProductException("Wrong Id!").setDefaultException(HttpStatus.NOT_ACCEPTABLE);
        }

    }
}
