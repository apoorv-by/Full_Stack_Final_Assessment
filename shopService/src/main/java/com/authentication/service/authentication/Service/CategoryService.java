package com.authentication.service.authentication.Service;

import com.authentication.service.authentication.Entity.Category;
import com.authentication.service.authentication.Repository.CategoryJDBC;
import com.authentication.service.authentication.Repository.CatergoryRepository;
import com.authentication.service.authentication.exceptionHandling.CategoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CatergoryRepository repository;

    @Autowired
    private CategoryJDBC jdbcQuery;

    public List<Category> getAllCategory(){
        return repository.findAll();
    }

    public Category getCategoryByField(Category category) throws CategoryException {
        try{
            if(category.getId()!=0){
                return repository.findById(category.getId()).get();
            }

            if(category.getType()!=null){
                return jdbcQuery.getCategoryByType(category.getType());
            }
            return null;
        }
        catch (Exception e){
            throw new CategoryException("NOT FOUND!").setDefaultException(HttpStatus.NOT_FOUND);
        }

    }


    public Category saveCategory(Category category) throws CategoryException {
        try{
            Category category_jdbc = jdbcQuery.getCategoryByType(category.getType());
            throw new CategoryException("Category already exist!").setDefaultException(HttpStatus.ALREADY_REPORTED);
        }
        catch (Exception e){
            if(e.getMessage().equals("Incorrect result size: expected 1, actual 0")){
                return repository.save(category);
            }
            return jdbcQuery.getCategoryByType(category.getType());

        }

    }

    public Category updateCategory(Category category) throws CategoryException {
        if(category.getId()==0){ throw new CategoryException("Id not found!.").setDefaultException(HttpStatus.NOT_FOUND);}
        try{
            Optional<Category> category_jb = repository.findById(category.getId());
            if(!category_jb.isPresent() | category_jb.get().getId()==0)
                throw new CategoryException("Category Id not found!.").setDefaultException(HttpStatus.ALREADY_REPORTED);
            return repository.save(category);
        }
        catch (Exception e){
            throw new CategoryException("Category Id not found!.").setDefaultException(HttpStatus.ALREADY_REPORTED);
        }


    }

    public String deleteCategory(int Id) throws CategoryException {
        try{
            Category category_jb = repository.findById(Id).get();
            repository.deleteById(Id);
            return "{\"message\":\"Successfully Deleted!\"}";
        }
        catch (Exception e) {
            throw new CategoryException("Category Id not found!.").setDefaultException(HttpStatus.ALREADY_REPORTED);
        }

    }

}
