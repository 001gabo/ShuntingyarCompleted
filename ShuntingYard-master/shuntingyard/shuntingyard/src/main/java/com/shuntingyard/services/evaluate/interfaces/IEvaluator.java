package com.shuntingyard.services.evaluate.interfaces;

import com.shuntingyard.pojo.evaluate.request.EvaluateRequest;

public interface IEvaluator<T> {
    public T getEvaluateResponse(EvaluateRequest evaluateRequest);
}
