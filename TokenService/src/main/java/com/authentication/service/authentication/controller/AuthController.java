package com.authentication.service.authentication.controller;



import com.authentication.service.authentication.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private TokenService tokenService;


    @GetMapping(value = "/getToken/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public String createToken(@PathVariable("id") String id)  {
        System.out.println(id);
        return  "{" +
                "\"message\":"+"\"Token for user:"+id+"\","+
                "\"token\":\""+tokenService.createToken(id)+"\"}";
    }

    @GetMapping(value = "/getUserId/{token}")
    public String getUser(@PathVariable("token") String token)  {
        return  "{" +
                "\"userId\":\""+tokenService.getUserIdFromToken(token)+"\"}";
    }

    @GetMapping(value = "/getTokenRandom",produces = MediaType.APPLICATION_JSON_VALUE)
    public String createToken_random()  {
        return  "{" +
                "\"message\":"+"\"Token for user:"+"id"+"\","+
                "\"token\":\""+tokenService.createToken("Random")+"\"}";
    }



}
