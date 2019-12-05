package com.shuntingyard.respository.implementations;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestaImplTest {

    @Test
    void getResult_OK() {
        RestaImpl restaImpl = new RestaImpl();
        double result = restaImpl.getResult(3.0,1.0);
        Assert.assertEquals(2.0,result,0);
    }

    @Test
    void getResult_BAD() {
        RestaImpl restaImpl = new RestaImpl();
        double result = restaImpl.getResult(3.0,1.0);
        Assert.assertNotEquals(5.0,result,0);
    }
}