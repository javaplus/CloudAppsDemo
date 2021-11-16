package com.barry.demo.baseapp.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController {

    @Value("${mymessage}")
    private String message;

    
    @GetMapping("/hello")
    public String helloThere(){
         return message;
    }

}