package com.shuntingyard.respository.implementations;

import com.shuntingyard.pojo.evaluate.request.EvaluateRequest;
import com.shuntingyard.pojo.evaluate.response.EvaluateResponse;
import com.shuntingyard.services.evaluate.interfaces.IEvaluator;
import com.shuntingyard.utils.InfixToPostFix;
import com.shuntingyard.utils.PostFixToResult;
import org.springframework.core.env.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EvaluatorImpl implements IEvaluator {

    private Logger log;
    private Environment env;
    private PostFixToResult postFixToResult;
    private InfixToPostFix infixToPostFix;

    public EvaluatorImpl(PostFixToResult postFixToResult,
                         InfixToPostFix infixToPostFix,
                         Environment env
    ){
        this.postFixToResult = postFixToResult;
        this.env = env;
        this.infixToPostFix = infixToPostFix;
        this.log =  LoggerFactory.getLogger(getClass());
    }

    @Override
    public EvaluateResponse getEvaluateResponse(EvaluateRequest evaluateRequest) {

        EvaluateResponse evaluateResponse = new EvaluateResponse();
        double finalResult = 0;
        LinkedList postFixQueue ;
        LinkedList auxPostFixQueue ;
        String postFixString = "";
        try{
            //Convirtiendo de infija a postfija
            postFixQueue = infixToPostFix.infixToPostfixEquation(evaluateRequest);
            auxPostFixQueue = (LinkedList) postFixQueue.clone();

            //Convirtiendo de postfija a resultado final
            finalResult = postFixToResult.postFixResult(auxPostFixQueue);

            //Sacando los elementos de la cola para generar el string que contiene la ecuacion postfija
            while(postFixQueue.size() != 0){
                postFixString += postFixQueue.poll();
            }

            return parserToDTO(evaluateRequest.getExp(),postFixString,finalResult);

        }catch (Exception e){
            log.error("EvaluatorImpl - Error: {}",e.getMessage());
            evaluateResponse.setMessage(env.getProperty("service.response.internal.error"));
            return evaluateResponse;
        }
    }

    private EvaluateResponse parserToDTO(String infixExp, String postfixExp,double value){

        EvaluateResponse evaluateResponse = new EvaluateResponse();

        evaluateResponse.setInfix(infixExp);
        evaluateResponse.setPostfix(postfixExp);
        evaluateResponse.setResult(value);
        evaluateResponse.setMessage("Success");

        return  evaluateResponse;
    }



}
