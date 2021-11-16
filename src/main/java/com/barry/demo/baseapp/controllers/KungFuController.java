package com.barry.demo.baseapp.controllers;

import com.barry.demo.baseapp.services.KungFuService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kungfu")
public class KungFuController {

    private static final Logger log = LoggerFactory.getLogger(KungFuController.class);

    @Autowired
    private KungFuService kungFuService;

    @GetMapping("/punch")
    public String incomingAction(@RequestParam("type")String type){
        System.out.println("I'm handling an in coming punch!");
        System.out.println("type=" + type);
        String responseToPunch = kungFuService.throwPunch(type);
        System.out.println("About to return this response=" + responseToPunch);
        
        return responseToPunch;
    }

}