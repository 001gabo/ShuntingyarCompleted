package com.shuntingyard.respository.implementations;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultiplicacionImplTest {

    @Test
    void getResult_OK() {
        MultiplicacionImpl multiplicacionImpl = new MultiplicacionImpl();
        double result = multiplicacionImpl.getResult(1.0,1.0);
        Assert.assertEquals(1.0,result,0);
    }

    @Test
    void getResult_BAD() {
        MultiplicacionImpl multiplicacionImpl = new MultiplicacionImpl();
        double result = multiplicacionImpl.getResult(1.0,1.0);
        Assert.assertNotEquals(3.0,result,0);
    }
}