package com.dummy.service.dummyService.controller;


import com.dummy.service.dummyService.model.Category;
import com.dummy.service.dummyService.model.Product;
import com.dummy.service.dummyService.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/consumer")
public class ConsumerController {
    @Autowired
    AuthenticatoinRestConsumer authenticatoinRestConsumer;

    @Autowired
    TokenRestConsumer tokenRestConsumer;

    @Autowired
    ShopManagementRestConsumer shopPublicRestConsumer;



    @GetMapping("/auth/get-users")
    List<User> getUsers(){
        return authenticatoinRestConsumer.getAll();
    }

    @PostMapping(value = "/auth/signUp" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public String savingAUser(@RequestBody Map<String, Object> user){
        try{

            return authenticatoinRestConsumer.savingAUser(user);

        }
        catch (Exception err){
            String response = err.getMessage();
            int index = response.indexOf("{\"errorCode");
            return response.substring(index,response.length()-1);
        }
    }

    @PostMapping(value = "/auth/login" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public String loginAUser(@RequestBody User user){
        try{
            String response = authenticatoinRestConsumer.loginAUser(user);
            return response.substring(0,response.length()-1)+
                    ",\"token\":"+tokenRestConsumer.createToken(user.getUsername())+"}";
        }
        catch (Exception err){
            String response = err.getMessage();
            System.out.println(response);
            int index = response.indexOf("{\"errorCode");
            return response.substring(index,response.length()-1);
        }
    }


    // JWT
    @GetMapping(value="/getToken/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public String createToken(@PathVariable("id") String id){
        return tokenRestConsumer.createToken(id);
    }

    @GetMapping("/getUserId/{token}")
    public String getUsedId(@PathVariable("token") String token){
        return tokenRestConsumer.getUserIdFromToken(token);
    }


    @GetMapping("/public/product/getAllProduct")
    public List<Product> getAllProduct(){
        return shopPublicRestConsumer.getAllProduct();
    }

    @PostMapping(value="/public/product/getProductByField",produces = MediaType.APPLICATION_JSON_VALUE)
    public String getProductByField(@RequestBody Product product){
        try{
            String response = shopPublicRestConsumer.getProductByField(product).toString();
            return response;
        }
        catch (Exception err){
            String response = err.getMessage();
            System.out.println(response);
            int index = response.indexOf("{\"errorCode");
            return response.substring(index,response.length()-1);
        }

    }

    @GetMapping("/public/category/getAllCategory")
    public List<Category> getAllCategory(){
        return shopPublicRestConsumer.getAllCategory();
    }

    @PostMapping(value="/public/category/getCategoryByField",produces = MediaType.APPLICATION_JSON_VALUE)
    public String getCategoryByField(@RequestBody Category category){
        try{
            String response = shopPublicRestConsumer.getCategoryByField(category).toString();
            return response;
        }
        catch (Exception err){
            String response = err.getMessage();
            System.out.println(response);
            int index = response.indexOf("{\"errorCode");
            return response.substring(index,response.length()-1);
        }
    }

    @PostMapping(value="/category/addCategory",produces = MediaType.APPLICATION_JSON_VALUE)
    public String addCategory(@RequestBody Category category){
        try{
            String response = shopPublicRestConsumer.addCategory(category).toString();
            return response;
        }
        catch (Exception err){
            String response = err.getMessage();
            System.out.println(response);
            int index = response.indexOf("{\"errorCode");
            return response.substring(index,response.length()-1);
        }
    }

    @PutMapping(value="/category/updateCategory",produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateCategory(@RequestBody Category category){
        try{
            Category response = shopPublicRestConsumer.updateCategory(category);
            if(response.getId()==0)
                return "{\"errorCode\":"+"406,"+
                    "\"message\":"+ "\"Wrong Id!\""+
                        "}";
            return response.toString();
        }
        catch (Exception err){
            String response = err.getMessage();
            System.out.println(err);
            int index = response.indexOf("{\"errorCode");
            return "response.substring(index,response.length()-1)";
        }
    }

    @DeleteMapping(value="/category/deleteCategory/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteCategory(@PathVariable("id") int Id){
        try{
            String response = shopPublicRestConsumer.deleteCategory(Id);
            System.out.println(response);
            return response;
        }
        catch (Exception err){
            String response = err.getMessage();
            System.out.println(response);
            int index = response.indexOf("{\"errorCode");
            return response.substring(index,response.length()-1);
        }
    }

    @PostMapping(value="/product/addProduct",produces = MediaType.APPLICATION_JSON_VALUE)
    public String addProduct(@RequestBody Product product){
        try{
            String response = shopPublicRestConsumer.addProduct(product).toString();
            return response;
        }
        catch (Exception err){
            String response = err.getMessage();
            System.out.println(response);
            int index = response.indexOf("{\"errorCode");
            return response.substring(index,response.length()-1);
        }
    }

    @GetMapping(value="/product/{pid}-with-{cid}",produces = MediaType.APPLICATION_JSON_VALUE)
    public String assignProduct(@PathVariable("pid") int pId, @PathVariable("cid") int cId){
        try{
            String response = shopPublicRestConsumer.assignProduct(pId,cId).toString();
            return response;
        }
        catch (Exception err){
            String response = err.getMessage();
            System.out.println(response);
            int index = response.indexOf("{\"errorCode");
            return response.substring(index,response.length()-1);
        }
    }

    @PutMapping(value="/product/updateProduct",produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateProduct(@RequestBody Product product){
        try{
            String response = shopPublicRestConsumer.updateProduct(product).toString();
            return response;
        }
        catch (Exception err){
            String response = err.getMessage();
            System.out.println(response);
            int index = response.indexOf("{\"errorCode");
            return response.substring(index,response.length()-1);
        }
    }

    @DeleteMapping(value="/product/deleteProduct/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteProduct(@PathVariable("id") int Id){
        try{
            String response = shopPublicRestConsumer.deleteProduct(Id).toString();
            return response;
        }
        catch (Exception err){
            String response = err.getMessage();
            System.out.println(response);
            int index = response.indexOf("{\"errorCode");
            return response.substring(index,response.length()-1);
        }
    }


}
