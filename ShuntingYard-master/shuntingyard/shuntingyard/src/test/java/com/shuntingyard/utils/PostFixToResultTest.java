package com.shuntingyard.utils;

import com.shuntingyard.respository.implementations.DivisionImpl;
import com.shuntingyard.respository.implementations.MultiplicacionImpl;
import com.shuntingyard.respository.implementations.RestaImpl;
import com.shuntingyard.respository.implementations.SumaImpl;
import com.shuntingyard.services.evaluate.interfaces.IOperations;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class PostFixToResultTest {

    @Test
    void postFixResult_OK() {
        IOperations iOperationsSuma = new SumaImpl();
        IOperations iOperationsResta = new RestaImpl();
        IOperations iOperationsMultiplicacion = new MultiplicacionImpl();
        IOperations iOperationsDivision = new DivisionImpl();

        PostFixToResult postFixToResult = new PostFixToResult(iOperationsSuma,iOperationsResta,
                iOperationsMultiplicacion, iOperationsDivision );

        LinkedList auxPostFixQueue = new LinkedList();

        auxPostFixQueue.offer('1');
        auxPostFixQueue.offer('2');
        auxPostFixQueue.offer('.');
        auxPostFixQueue.offer('5');
        auxPostFixQueue.offer('3');
        auxPostFixQueue.offer('/');
        auxPostFixQueue.offer('4');
        auxPostFixQueue.offer('*');
        auxPostFixQueue.offer('+');

        double resultValue = postFixToResult.postFixResult(auxPostFixQueue);

        assertEquals(4.333333333333334, resultValue);
    }


    @Test
    void postFixResult_ERROR() {
        IOperations iOperationsSuma = new SumaImpl();
        IOperations iOperationsResta = new RestaImpl();
        IOperations iOperationsMultiplicacion = new MultiplicacionImpl();
        IOperations iOperationsDivision = new DivisionImpl();

        PostFixToResult postFixToResult = new PostFixToResult(iOperationsSuma,iOperationsResta,
                iOperationsMultiplicacion, iOperationsDivision );

        LinkedList auxPostFixQueue = new LinkedList();

        auxPostFixQueue.offer('-');
        auxPostFixQueue.offer('2');
        auxPostFixQueue.offer('.');
        auxPostFixQueue.offer('5');
        auxPostFixQueue.offer('3');
        auxPostFixQueue.offer('/');
        auxPostFixQueue.offer('4');
        auxPostFixQueue.offer(')');
        auxPostFixQueue.offer('+');

        double resultValue = postFixToResult.postFixResult(auxPostFixQueue);

        assertNotNull(resultValue);
    }
}