package com.wang.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {
    @Autowired
    RestTemplate restTemplate;
    @GetMapping("/")
    @ResponseBody
    public String buyTicket(){
        String str = restTemplate.getForObject("http://PROVIDER-TICKET/ticket", String.class);
        return str;
    }
}
