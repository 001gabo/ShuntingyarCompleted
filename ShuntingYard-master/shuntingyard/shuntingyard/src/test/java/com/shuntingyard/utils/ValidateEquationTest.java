package com.shuntingyard.utils;

import com.shuntingyard.pojo.evaluate.request.EvaluateRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidateEquationTest {

    @Test
    void validateEquation_OK() {
        ValidateEquation validateEquation = new ValidateEquation();
        EvaluateRequest evaluateRequest = new EvaluateRequest();
        evaluateRequest.setExp("1+2.5/3*4");

        int actualResponse = validateEquation.ValidateEquation(evaluateRequest);
        assertEquals(1,actualResponse);
    }

    @Test
    void validateEquation_PARENTHESES() {
        ValidateEquation validateEquation = new ValidateEquation();
        EvaluateRequest evaluateRequest = new EvaluateRequest();
        evaluateRequest.setExp("(1+2.5/3*4)");

        int actualResponse = validateEquation.ValidateEquation(evaluateRequest);
        assertEquals(2,actualResponse);
    }

    @Test
    void validateEquation_NEGATIVE_NUMBER() {
        ValidateEquation validateEquation = new ValidateEquation();
        EvaluateRequest evaluateRequest = new EvaluateRequest();
        evaluateRequest.setExp("-1+2.5/3*4");

        int actualResponse = validateEquation.ValidateEquation(evaluateRequest);
        assertEquals(2,actualResponse);
    }

    @Test
    void validateEquation_MIXED_SYMBOLS() {
        ValidateEquation validateEquation = new ValidateEquation();
        EvaluateRequest evaluateRequest = new EvaluateRequest();
        evaluateRequest.setExp("1+-2.5/3*4");

        int actualResponse = validateEquation.ValidateEquation(evaluateRequest);
        assertEquals(2,actualResponse);
    }

}