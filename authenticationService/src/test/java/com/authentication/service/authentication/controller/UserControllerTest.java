package com.authentication.service.authentication.controller;


import com.authentication.service.authentication.AuthenticationApplication;
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

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringJUnit4ClassRunner.class) // for running this with junit4
@ContextConfiguration(classes = AuthenticationApplication.class) // Run it setup method for default data
@SpringBootTest // spring test
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING) // to excute the test methods in order (based on name)
public class UserControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext userContext; // autowired the configuration

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(userContext).build();
    }

    @Test
    public void printCheckJsonBody() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.
                        get("/users/getAll").
                        accept(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$.*").exists()).andDo(print());
    }

    @Test
    public void getAllUsersTest() throws  Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.
                        get("/users/getAll").
                        accept(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$",hasSize(5))).andDo(print());
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
    public void verifySaveUser() throws Exception{
        String username = "apoorv1";
        Map<String,Object> user = new HashMap<>();
        user.put("id","636284b798908a02aa6f0062");
        user.put("username",username);
        user.put("password","12345");
        user.put("confirm password","12345");
        user.put("email","apoorv1");
        mockMvc.perform(MockMvcRequestBuilders.post("/users/registration")
                        .content(asJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(jsonPath("$.data.username").value(username))
                .andExpect(jsonPath("$.data.email").value("apoorv1")).andDo(print());
    }

    @Test
    public void verifySaveUser_AlreadyExist() throws Exception {
        //{"errorCode":405,"message":"Username already exist! [Method not allowed]"}
        Map<String,Object> user = new HashMap<>();
        user.put("id","636284b798908a02aa6f0062");
        user.put("username","username");
        user.put("password","12345");
        user.put("confirm password","12345");
        user.put("email","apoorv");
        mockMvc.perform(
                        MockMvcRequestBuilders.
                                post("/users/registration")
                                .content(asJsonString(user))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                ).andExpect(jsonPath("$.errorCode").value(405))
                .andExpect(jsonPath("$.message").value("Username already exist! [Method not allowed]")).andDo(print());
    }

    @Test
    public void verifyLoginUser() throws Exception {
        //{"errorCode":405,"message":"Username already exist! [Method not allowed]"}
        Map<String,Object> user = new HashMap<>();
        user.put("username","Apoorv");
        user.put("password","1234");
        mockMvc.perform(
                        MockMvcRequestBuilders.
                                post("/users/login")
                                .content(asJsonString(user))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                ).andExpect(jsonPath("$.message").value(" Successfully Login. "))
                .andExpect(jsonPath("$.status").value(true)).andDo(print());
    }
}
