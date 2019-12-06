package com.shuntingyard.Mocks;

import com.shuntingyard.pojo.evaluate.request.EvaluateRequest;
import com.shuntingyard.pojo.evaluate.response.EvaluateResponse;
import com.shuntingyard.services.evaluate.interfaces.IEvaluator;

public class IEvaluatorErrorMock implements IEvaluator {
    @Override
    public EvaluateResponse getEvaluateResponse(EvaluateRequest evaluateRequest) {
        EvaluateResponse evaluateResponse = new EvaluateResponse();
        return evaluateResponse;
    }
}
