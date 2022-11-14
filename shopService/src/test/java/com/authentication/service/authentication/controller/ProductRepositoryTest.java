package com.authentication.service.authentication.controller;
import com.authentication.service.authentication.Entity.Category;
import com.authentication.service.authentication.Entity.Product;
import com.authentication.service.authentication.ShopServiceApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@RunWith(SpringJUnit4ClassRunner.class) // for running this with junit4
@ContextConfiguration(classes = ShopServiceApplication.class) // Run it setup method for default data
@SpringBootTest // spring test
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING) // to excute the test methods in order (based on name)
public class ProductRepositoryTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext movieContext; // autowired the configuration

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(movieContext).build();
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            System.out.println(jsonContent);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void printCheckJsonBody() throws Exception{
        Category category = new Category(2, "electric");
        mockMvc.perform(
                MockMvcRequestBuilders.
                        get("/shop/product/getAllProduct").
                        accept(MediaType.APPLICATION_JSON)
        ).andDo(print());
    }


    @Test
    public void verifyAllProduct() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.
                        get("/shop/product/getAllProduct").
                        accept(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$",hasSize(2))).andDo(print());
    }

    @Test
    public void verifyassignCategoryToProduct() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.
                        get("/shop/product/3-with-1").
                        accept(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$.categories",hasSize(1))).andDo(print());
    }

    @Test
    public void verifySaveProduct() throws Exception{
//        Content type = application/json
//        Body = {"id":5,"name":"AC","price":"$10000","description":"electronic","categories":[]}
//        Forwarded URL = null
        Product product = new Product(5,"AC","$10000","electronic");

        mockMvc.perform(MockMvcRequestBuilders.post("/shop/product/addProduct")
                        .content(asJsonString(product))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$.name").value("AC"))
                .andDo(print());
    }

    @Test
    public void verifyUpdateProduct() throws Exception{
        Product product = new Product(3,"DC",null,null);
        mockMvc.perform(MockMvcRequestBuilders.put("/shop/product/updateProduct")
                        .content(asJsonString(product))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$.name").value("DC"))
                .andExpect(jsonPath("$.price").value(null))
                .andDo(print());
    }

    @Test
    public void verifygetProductByField() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/shop/product/getProductByField")
                        .content(asJsonString(new Product(3,null,null,null)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.name").value("Banana"))
                .andExpect(jsonPath("$.price").value("$1.1"))

                .andDo(print());
    }

    @Test
    public void verifydeleteMovie() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.delete("/shop/product/deleteProduct/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(jsonPath("$.message").value("Successfully Deleted!"))
                .andDo(print());
    }




}
