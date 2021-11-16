package com.barry.demo.baseapp.services;

import org.springframework.stereotype.Service;

@Service
public class KungFuService {
    

    /**
     * Throws a kungfu punch of specified type and returns expected result from said punch.
     * 
     * @param type
     * @return Type of result expected
     */
    public String throwPunch(String typeOfPunch){

        if(typeOfPunch.equalsIgnoreCase("light")) {
            return "mild agitation";
        }
        if(typeOfPunch.equalsIgnoreCase("high")){
            return "nose bleed";        
        }if(typeOfPunch.equalsIgnoreCase("low")){
            return "gut ache";        
        }else{
            return "disdain";
        }
    }

}