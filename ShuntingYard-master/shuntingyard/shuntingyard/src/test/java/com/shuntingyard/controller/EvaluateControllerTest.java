package com.shuntingyard.controller;

import com.shuntingyard.Mocks.IEvaluatorMock;
import com.shuntingyard.Mocks.MockEnv;
import com.shuntingyard.pojo.evaluate.request.EvaluateRequest;
import com.shuntingyard.process.EvaluateProcess;
import com.shuntingyard.utils.ValidateEquation;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class EvaluateControllerTest {

    @Test
    void evaluateInfix_OK() {
        MockEnv mockEnv = new MockEnv();
        IEvaluatorMock iEvaluatorMock = new IEvaluatorMock();
        EvaluateProcess evaluateProcess = new EvaluateProcess(iEvaluatorMock);
        ValidateEquation validateEquation = new ValidateEquation();
        EvaluateRequest evaluateRequest = new EvaluateRequest();
        evaluateRequest.setExp("2+3");
        EvaluateController evaluateController = new EvaluateController(mockEnv,evaluateProcess,validateEquation);

        ResponseEntity<?> responseEntity = evaluateController.EvaluateInfix(evaluateRequest);

        Assert.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }

    @Test
    void evaluateInfix_BADREQUEST() {
        MockEnv mockEnv = new MockEnv();
        IEvaluatorMock iEvaluatorMock = new IEvaluatorMock();
        EvaluateProcess evaluateProcess = new EvaluateProcess(iEvaluatorMock);
        ValidateEquation validateEquation = new ValidateEquation();
        EvaluateRequest evaluateRequest = new EvaluateRequest();
        evaluateRequest.setExp("-2+3");
        EvaluateController evaluateController = new EvaluateController(mockEnv,evaluateProcess,validateEquation);

        ResponseEntity<?> responseEntity = evaluateController.EvaluateInfix(evaluateRequest);

        Assert.assertEquals(HttpStatus.BAD_REQUEST,responseEntity.getStatusCode());
    }
}