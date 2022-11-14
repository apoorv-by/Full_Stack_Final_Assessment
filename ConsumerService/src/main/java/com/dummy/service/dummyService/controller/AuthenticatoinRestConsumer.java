package com.dummy.service.dummyService.controller;


import com.dummy.service.dummyService.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient("authentication-service/users")
public interface AuthenticatoinRestConsumer {
    @GetMapping("/registration")
    String savingAUser(@RequestBody Map<String,Object> user);

    @GetMapping("/getAll")
    List<User> getAll();

    @PostMapping(value = "/login" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public String loginAUser(@RequestBody User user);
}
