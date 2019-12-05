package com.shuntingyard.utils;

import com.shuntingyard.services.evaluate.interfaces.IOperations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Stack;

@Component
public class PostFixToResult {

    private Logger log;
    private IOperations iOperationsSuma;
    private IOperations iOperationsResta;
    private IOperations iOperationsMultiplicacion;
    private IOperations iOperationsDivision;

    public PostFixToResult(@Qualifier("SumaImpl")IOperations iOperationsSuma,
                           @Qualifier("RestaImpl")IOperations iOperationsResta,
                           @Qualifier("MultiplicacionImpl")IOperations iOperationsMultiplicacion,
                           @Qualifier("DivisionImpl")IOperations iOperationsDivision){

        this.iOperationsSuma = iOperationsSuma;
        this.iOperationsResta = iOperationsResta;
        this.iOperationsMultiplicacion = iOperationsMultiplicacion;
        this.iOperationsDivision = iOperationsDivision;
        this.log =  LoggerFactory.getLogger(getClass());

    }
    public double postFixResult(LinkedList auxPostFixQueue){

        double result =0;
        Stack pilaEvaluadora = new Stack();

        try{
            while(auxPostFixQueue.size() != 0){
                if(Character.isDigit(auxPostFixQueue.peek().toString().charAt(0))){ //chequeando si el caracter es un digito
                    pilaEvaluadora.push(auxPostFixQueue.poll().toString().charAt(0));
                }else{
                    switch (auxPostFixQueue.poll().toString().charAt(0)) {
                        case '+':
                            result =iOperationsSuma.getResult(Double.parseDouble(pilaEvaluadora.pop().toString()),
                                    Double.parseDouble(pilaEvaluadora.pop().toString()));
                            pilaEvaluadora.push(result);
                            break;
                        case '-':
                            result =iOperationsResta.getResult(Double.parseDouble(pilaEvaluadora.pop().toString()),
                                    Double.parseDouble(pilaEvaluadora.pop().toString()));
                            pilaEvaluadora.push(result);
                            break;
                        case '*':
                            result =iOperationsMultiplicacion.getResult(Double.parseDouble(pilaEvaluadora.pop().toString()),
                                    Double.parseDouble(pilaEvaluadora.pop().toString()));
                            pilaEvaluadora.push(result);
                            break;
                        case '/':
                            result =iOperationsDivision.getResult(Double.parseDouble(pilaEvaluadora.pop().toString()),
                                    Double.parseDouble(pilaEvaluadora.pop().toString()));
                            pilaEvaluadora.push(result);
                            break;
                        case '.':
                            pilaEvaluadora.push(auxPostFixQueue.poll().toString().charAt(0));
                            pilaEvaluadora.push(createDoubleNumber(pilaEvaluadora));
                            break;
                    }
                }
            }
        }catch (Exception e){
            log.error("PostFixToResult - Error: {}",e.getMessage());
        }

        return result;
    }

    private double createDoubleNumber(Stack pilaEvaluadora){
        String finalDigit = pilaEvaluadora.pop().toString();
        String initDigit = pilaEvaluadora.pop().toString();
        return Double.parseDouble(initDigit+"."+finalDigit);
    }
}
