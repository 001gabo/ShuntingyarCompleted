package com.shuntingyard.respository.implementations;

import com.shuntingyard.services.evaluate.interfaces.IOperations;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("DivisionImpl")
public class DivisionImpl implements IOperations {

    @Override
    public double getResult(double number1,double number2) {
        return number2/number1;
    }
}
