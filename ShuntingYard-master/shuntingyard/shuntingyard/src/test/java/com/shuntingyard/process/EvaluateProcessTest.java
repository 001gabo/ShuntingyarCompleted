package com.shuntingyard.process;

import com.shuntingyard.Mocks.IEvaluatorErrorMock;
import com.shuntingyard.Mocks.IEvaluatorInternalErrorMock;
import com.shuntingyard.Mocks.IEvaluatorOkMock;
import com.shuntingyard.Mocks.MockEnv;
import com.shuntingyard.pojo.evaluate.request.EvaluateRequest;
import com.shuntingyard.pojo.evaluate.response.EvaluateResponse;
import com.shuntingyard.services.evaluate.interfaces.IEvaluator;
import com.shuntingyard.utils.ValidateEquation;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class EvaluateProcessTest {

    @Test
    void process_OK() {
        IEvaluatorOkMock iEvaluatorOkMock = new IEvaluatorOkMock();
        MockEnv mockEnv = new MockEnv();
        ValidateEquation validateEquation = new ValidateEquation();
        EvaluateProcess evaluateProcess = new EvaluateProcess(iEvaluatorOkMock,mockEnv,validateEquation);
        EvaluateRequest evaluateRequest = new EvaluateRequest();

        evaluateRequest.setExp("1+2.5/3*4");

        ResponseEntity<?> processResponse = evaluateProcess.process(evaluateRequest);
        assertEquals(HttpStatus.OK,processResponse.getStatusCode());

    }

    @Test
    void process_PARENTHESIS() {
        IEvaluatorErrorMock iEvaluatorErrorMock = new IEvaluatorErrorMock();
        MockEnv mockEnv = new MockEnv();
        ValidateEquation validateEquation = new ValidateEquation();
        EvaluateProcess evaluateProcess = new EvaluateProcess(iEvaluatorErrorMock,mockEnv,validateEquation);
        EvaluateRequest evaluateRequest = new EvaluateRequest();

        evaluateRequest.setExp("(1+2.5/3*4)");
        ResponseEntity<?> processResponse = evaluateProcess.process(evaluateRequest);
        assertEquals(HttpStatus.BAD_REQUEST,processResponse.getStatusCode());

    }

    @Test
    void process_NEGATIVE_NUMBER() {
        IEvaluatorErrorMock iEvaluatorErrorMock = new IEvaluatorErrorMock();
        MockEnv mockEnv = new MockEnv();
        ValidateEquation validateEquation = new ValidateEquation();
        EvaluateProcess evaluateProcess = new EvaluateProcess(iEvaluatorErrorMock,mockEnv,validateEquation);
        EvaluateRequest evaluateRequest = new EvaluateRequest();

        evaluateRequest.setExp("-1+2.5/3*4");
        ResponseEntity<?> processResponse = evaluateProcess.process(evaluateRequest);
        assertEquals(HttpStatus.BAD_REQUEST,processResponse.getStatusCode());

    }

    @Test
    void process_MIXED_SYMBOLS() {
        IEvaluatorErrorMock iEvaluatorErrorMock = new IEvaluatorErrorMock();
        MockEnv mockEnv = new MockEnv();
        ValidateEquation validateEquation = new ValidateEquation();
        EvaluateProcess evaluateProcess = new EvaluateProcess(iEvaluatorErrorMock,mockEnv,validateEquation);
        EvaluateRequest evaluateRequest = new EvaluateRequest();

        evaluateRequest.setExp("-1+-2.5/3*4");
        ResponseEntity<?> processResponse = evaluateProcess.process(evaluateRequest);
        assertEquals(HttpStatus.BAD_REQUEST,processResponse.getStatusCode());

    }

    @Test
    void process_INTERNAL_ERROR() {
        IEvaluatorInternalErrorMock iEvaluatorInternalErrorMock = new IEvaluatorInternalErrorMock();
        MockEnv mockEnv = new MockEnv();
        ValidateEquation validateEquation = new ValidateEquation();
        EvaluateProcess evaluateProcess = new EvaluateProcess(iEvaluatorInternalErrorMock,mockEnv,validateEquation);
        EvaluateRequest evaluateRequest = new EvaluateRequest();

        evaluateRequest.setExp("1+2.5/3*4");
        ResponseEntity<?> processResponse = evaluateProcess.process(evaluateRequest);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,processResponse.getStatusCode());

    }
}