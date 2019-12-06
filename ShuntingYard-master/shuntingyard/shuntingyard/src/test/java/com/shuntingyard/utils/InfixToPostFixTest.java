package com.shuntingyard.utils;

import com.shuntingyard.pojo.evaluate.request.EvaluateRequest;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

class InfixToPostFixTest {

    @Test
    void infixToPostfixEquation_OK(){
        InfixToPostFix infixToPostFix = new InfixToPostFix();
        EvaluateRequest evaluateRequest = new EvaluateRequest();
        evaluateRequest.setExp("1+2.5/3*4");

        LinkedList linkedList = infixToPostFix.infixToPostfixEquation(evaluateRequest);

        Assert.assertNotNull(linkedList);
    }

    @Test
    void infixToPostfixEquation_ERROR(){
        InfixToPostFix infixToPostFix = new InfixToPostFix();
        EvaluateRequest evaluateRequest = new EvaluateRequest();
        evaluateRequest.setExp("(1+2.5/3*4");

        LinkedList linkedList = infixToPostFix.infixToPostfixEquation(evaluateRequest);

        Assert.assertNotNull(linkedList);
    }
}