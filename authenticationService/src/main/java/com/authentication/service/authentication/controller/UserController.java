package com.authentication.service.authentication.controller;


import com.authentication.service.authentication.model.User;
import com.authentication.service.authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/registration" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public String savingAUser(@RequestBody Map<String,Object> userData) throws Exception {
        return userService.signUp(userData);
    }

    @PostMapping(value = "/login" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public String loginAUser(@RequestBody Map<String,Object> userData) throws Exception {
        return userService.loginService(userData);
    }

    @GetMapping(value="/getAll",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll(){

        return userService.getAll();
    }


}
