package com.shuntingyard.utils;

import com.shuntingyard.pojo.evaluate.request.EvaluateRequest;
import com.shuntingyard.pojo.evaluate.response.EvaluateResponse;
import org.springframework.stereotype.Component;

@Component
public class ValidateEquation {

    public int ValidateEquation(EvaluateRequest request) {

        EvaluateResponse evaluateResponse = new EvaluateResponse();

        //Si contiene uno de estos casos es un bad request
        if(request.getExp().contains("(")
                || request.getExp().contains("(")
                || request.getExp().charAt(0) == '-'
                || request.getExp().contains("--")
                || request.getExp().contains("+-")
                || request.getExp().contains("-+")
        ){
            return 2;
        }else{ //De lo contrario es un request valido
            return 1;
        }
    }
}
