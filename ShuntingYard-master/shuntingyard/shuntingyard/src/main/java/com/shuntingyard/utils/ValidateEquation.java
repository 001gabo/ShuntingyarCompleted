package com.shuntingyard.utils;

import com.shuntingyard.pojo.evaluate.request.EvaluateRequest;
import com.shuntingyard.pojo.evaluate.response.EvaluateResponse;
import org.springframework.stereotype.Component;

@Component
public class ValidateEquation {

    public int ValidateEquation(EvaluateRequest request) {

        EvaluateResponse evaluateResponse = new EvaluateResponse();

        if(request.getExp().contains("(")
                || request.getExp().contains("(")
                ||  "-".equals(request.getExp().charAt(0))
        ){
            return 2;
        }else{
            return 1;
        }
    }
}
