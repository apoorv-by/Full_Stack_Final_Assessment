package com.authentication.service.authentication.controller;
import com.authentication.service.authentication.Entity.Category;
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
public class CategoryRepositoryTest {

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
                        get("/shop/category/getAllCategory").
                        accept(MediaType.APPLICATION_JSON)
        ).andDo(print());
    }


    @Test
    public void verifyAllCategory() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.
                        get("/shop/category/getAllCategory").
                        accept(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$",hasSize(2))).andDo(print());
    }

    @Test
    public void verifySaveCategory() throws Exception{
//        Content type = application/json
//        Body = {"id":1,"type":"electric"}
//        Forwarded URL = null
        Category category = new Category(3, "electric");
        mockMvc.perform(MockMvcRequestBuilders.post("/shop/category/addCategory")
                .content(asJsonString(category))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.type").value("electric"))
                .andDo(print());
    }

    @Test
    public void verifyUpdateCategory() throws Exception{
//        Content type = application/json
//        Body = {"id":1,"type":"electric"}
//        Forwarded URL = null
        Category category = new Category(2, "home");
        mockMvc.perform(MockMvcRequestBuilders.put("/shop/category/updateCategory")
                        .content(asJsonString(category))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.type").value("home"))
                .andDo(print());
    }

    @Test
    public void verifygetCategoryByField() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/shop/category/getCategoryByField")
                        .content(asJsonString(new Category(0,"food")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.type").value("food"))
                .andDo(print());
    }

    @Test
    public void verifydeleteCategory() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.delete("/shop/category/deleteCategory/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(jsonPath("$.message").value("Successfully Deleted!"))
                .andDo(print());
    }




}
