package com.shuntingyard.respository.implementations;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SumaImplTest {

    @Test
    void getResult_OK() {
        SumaImpl sumaImpl = new SumaImpl();
        double result = sumaImpl.getResult(1.0,1.0);
        Assert.assertEquals(2.0,result,0);
    }

    @Test
    void getResult_BAD() {
        SumaImpl sumaImpl = new SumaImpl();
        double result = sumaImpl.getResult(1.0,1.0);
        Assert.assertNotEquals(3.0,result,0);
    }
}