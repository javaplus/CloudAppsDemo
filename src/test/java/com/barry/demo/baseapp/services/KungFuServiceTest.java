package com.barry.demo.baseapp.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.barry.demo.baseapp.services.KungFuService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class KungFuServiceTest {
    
    // the class under test
    private KungFuService kungFuService;

    @BeforeEach
    public void initTests()
    {
        kungFuService = new KungFuService();
    }

    @Test
    public void throwPunch_given_light_return_mild_agitation(){
        String response = kungFuService.throwPunch("light");
        assertEquals("mild agitation",response);
    }

    @Test
    public void throwPunch_given_high_return_nose_bleed(){
        String response = kungFuService.throwPunch("high");
        assertEquals("nose bleed",response);
    }

    @Test
    public void throwPunch_given_low_return_gut_ache(){
        String response = kungFuService.throwPunch("low");
        assertEquals("gut ache",response);
    }

    
}