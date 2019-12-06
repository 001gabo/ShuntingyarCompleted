package com.shuntingyard.controller;

import com.shuntingyard.Mocks.IEvaluatorErrorMock;
import com.shuntingyard.Mocks.IEvaluatorInternalErrorMock;
import com.shuntingyard.Mocks.IEvaluatorOkMock;
import com.shuntingyard.Mocks.MockEnv;
import com.shuntingyard.pojo.evaluate.request.EvaluateRequest;
import com.shuntingyard.process.EvaluateProcess;
import com.shuntingyard.utils.ValidateEquation;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class EvaluateControllerTest {

    @Test
    void evaluateInfix_OK() {
        MockEnv mockEnv = new MockEnv();
        IEvaluatorOkMock iEvaluatorOkMock = new IEvaluatorOkMock();
        ValidateEquation validateEquation = new ValidateEquation();
        EvaluateRequest evaluateRequest = new EvaluateRequest();
        EvaluateProcess evaluateProcess = new EvaluateProcess(iEvaluatorOkMock,mockEnv,validateEquation);
        EvaluateController evaluateController = new EvaluateController(mockEnv,evaluateProcess);

        evaluateRequest.setExp("1+2.5/3*4");
        ResponseEntity<?> controllerResponse = evaluateController.EvaluateInfix(evaluateRequest);
        Assert.assertEquals(HttpStatus.OK,controllerResponse.getStatusCode());
    }

    @Test
    void evaluateInfix_PARENTHESIS() {
        MockEnv mockEnv = new MockEnv();
        IEvaluatorErrorMock iEvaluatorErrorMock = new IEvaluatorErrorMock();
        ValidateEquation validateEquation = new ValidateEquation();
        EvaluateRequest evaluateRequest = new EvaluateRequest();
        EvaluateProcess evaluateProcess = new EvaluateProcess(iEvaluatorErrorMock,mockEnv,validateEquation);
        EvaluateController evaluateController = new EvaluateController(mockEnv,evaluateProcess);

        evaluateRequest.setExp("(1+2.5/3*4)");
        ResponseEntity<?> controllerResponse = evaluateController.EvaluateInfix(evaluateRequest);
        Assert.assertEquals(HttpStatus.BAD_REQUEST,controllerResponse.getStatusCode());
    }

    @Test
    void evaluateInfix_NEGATIVE_NUMBER() {
        MockEnv mockEnv = new MockEnv();
        IEvaluatorErrorMock iEvaluatorErrorMock = new IEvaluatorErrorMock();
        ValidateEquation validateEquation = new ValidateEquation();
        EvaluateRequest evaluateRequest = new EvaluateRequest();
        EvaluateProcess evaluateProcess = new EvaluateProcess(iEvaluatorErrorMock,mockEnv,validateEquation);
        EvaluateController evaluateController = new EvaluateController(mockEnv,evaluateProcess);

        evaluateRequest.setExp("-1+2.5/3*4");
        ResponseEntity<?> controllerResponse = evaluateController.EvaluateInfix(evaluateRequest);
        Assert.assertEquals(HttpStatus.BAD_REQUEST,controllerResponse.getStatusCode());
    }

    @Test
    void evaluateInfix_MIXED_SYMBOLS() {
        MockEnv mockEnv = new MockEnv();
        IEvaluatorErrorMock iEvaluatorErrorMock = new IEvaluatorErrorMock();
        ValidateEquation validateEquation = new ValidateEquation();
        EvaluateRequest evaluateRequest = new EvaluateRequest();
        EvaluateProcess evaluateProcess = new EvaluateProcess(iEvaluatorErrorMock,mockEnv,validateEquation);
        EvaluateController evaluateController = new EvaluateController(mockEnv,evaluateProcess);

        evaluateRequest.setExp("-1+-2.5/3*4");
        ResponseEntity<?> controllerResponse = evaluateController.EvaluateInfix(evaluateRequest);
        Assert.assertEquals(HttpStatus.BAD_REQUEST,controllerResponse.getStatusCode());
    }

    @Test
    void evaluateInfix_INTERNAL_ERROR() {
        MockEnv mockEnv = new MockEnv();
        IEvaluatorInternalErrorMock iEvaluatorInternalErrorMock = new IEvaluatorInternalErrorMock();
        ValidateEquation validateEquation = new ValidateEquation();
        EvaluateRequest evaluateRequest = new EvaluateRequest();
        EvaluateProcess evaluateProcess = new EvaluateProcess(iEvaluatorInternalErrorMock,mockEnv,validateEquation);
        EvaluateController evaluateController = new EvaluateController(mockEnv,evaluateProcess);

        evaluateRequest.setExp("1+2.5/3*4");
        ResponseEntity<?> controllerResponse = evaluateController.EvaluateInfix(evaluateRequest);
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,controllerResponse.getStatusCode());
    }
}