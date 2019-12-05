package com.shuntingyard.respository.implementations;

import com.shuntingyard.services.evaluate.interfaces.IOperations;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("RestaImpl")
public class RestaImpl implements IOperations {

    @Override
    public double getResult(double number1, double number2) {
        return number1 - number2;
    }
}
