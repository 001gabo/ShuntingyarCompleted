package com.shuntingyard.respository.implementations;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DivisionImplTest {

    @Test
    void getResult_OK() {
        DivisionImpl divisionImpl = new DivisionImpl();
        double result = divisionImpl.getResult(1.0,1.0);
        Assert.assertEquals(1.0,result,0);
    }

    @Test
    void getResult_BAD() {
        DivisionImpl divisionImpl = new DivisionImpl();
        double result = divisionImpl.getResult(1.0,1.0);;
        Assert.assertNotEquals(3.0,result,0);
    }
}