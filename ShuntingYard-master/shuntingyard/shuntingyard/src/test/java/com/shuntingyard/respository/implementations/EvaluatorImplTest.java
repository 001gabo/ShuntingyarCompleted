package com.shuntingyard.respository.implementations;

import com.shuntingyard.Mocks.MockEnv;
import com.shuntingyard.pojo.evaluate.request.EvaluateRequest;
import com.shuntingyard.pojo.evaluate.response.EvaluateResponse;
import com.shuntingyard.services.evaluate.interfaces.IOperations;
import com.shuntingyard.utils.InfixToPostFix;
import com.shuntingyard.utils.PostFixToResult;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.core.env.Environment;

import static org.junit.jupiter.api.Assertions.*;

class EvaluatorImplTest {

    @Test
    void getEvaluateResponse_OK() {
        MockEnv mockEnv = new MockEnv();
        EvaluateRequest evaluateRequest = new EvaluateRequest();
        evaluateRequest.setExp("1+2.5/3*4");

        IOperations iOperationsSuma = new SumaImpl();
        IOperations iOperationsResta = new RestaImpl();
        IOperations iOperationsMultiplicacion = new MultiplicacionImpl();
        IOperations iOperationsDivision = new DivisionImpl();
        PostFixToResult postFixToResult = new PostFixToResult(iOperationsSuma,iOperationsResta,
                iOperationsMultiplicacion, iOperationsDivision );

        InfixToPostFix infixToPostFix = new InfixToPostFix();

        EvaluatorImpl evaluatorImpl = new EvaluatorImpl(postFixToResult,infixToPostFix,mockEnv);

        EvaluateResponse evaluateResponse = evaluatorImpl.getEvaluateResponse(evaluateRequest);

        assertNotNull(evaluateResponse);
    }

    @Test
    void testGetEvaluateResponse_BAD() {
        MockEnv mockEnv = new MockEnv();
        EvaluateRequest evaluateRequest = new EvaluateRequest();
        evaluateRequest.setExp("(1+2.5/3*4)");

        IOperations iOperationsSuma = new SumaImpl();
        IOperations iOperationsResta = new RestaImpl();
        IOperations iOperationsMultiplicacion = new MultiplicacionImpl();
        IOperations iOperationsDivision = new DivisionImpl();
        PostFixToResult postFixToResult = new PostFixToResult(iOperationsSuma,iOperationsResta,
                iOperationsMultiplicacion, iOperationsDivision );

        InfixToPostFix infixToPostFix = new InfixToPostFix();

        EvaluatorImpl evaluatorImpl = new EvaluatorImpl(postFixToResult,infixToPostFix,mockEnv);

        EvaluateResponse evaluateResponse = evaluatorImpl.getEvaluateResponse(evaluateRequest);

        assertNotNull(evaluateResponse);
    }
}