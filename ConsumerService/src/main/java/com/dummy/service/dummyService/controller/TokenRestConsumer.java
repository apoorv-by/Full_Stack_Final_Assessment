package com.dummy.service.dummyService.controller;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("token-service/auth")
public interface TokenRestConsumer {
    @GetMapping(value="/getToken/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public String createToken(@PathVariable("id") String id);

    @GetMapping("/getUserId/{token}")
    public String getUserIdFromToken(@PathVariable("token") String token);

    @GetMapping(value = "/getTokenRandom",produces = MediaType.APPLICATION_JSON_VALUE)
    public String createToken_random();
}
