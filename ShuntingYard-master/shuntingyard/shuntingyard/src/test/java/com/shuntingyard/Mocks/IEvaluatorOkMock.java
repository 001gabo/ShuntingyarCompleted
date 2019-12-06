package com.shuntingyard.Mocks;

import com.shuntingyard.pojo.evaluate.request.EvaluateRequest;
import com.shuntingyard.pojo.evaluate.response.EvaluateResponse;
import com.shuntingyard.services.evaluate.interfaces.IEvaluator;

public class IEvaluatorOkMock implements IEvaluator {
    @Override
    public EvaluateResponse getEvaluateResponse(EvaluateRequest evaluateRequest) {
        EvaluateResponse evaluateResponse = new EvaluateResponse();
        evaluateResponse.setInfix(evaluateRequest.getExp());
        evaluateResponse.setPostfix("23+");
        evaluateResponse.setResult(5.0);
        evaluateResponse.setMessage("Success");
        return evaluateResponse;
    }
}
