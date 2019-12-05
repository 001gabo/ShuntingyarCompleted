package com.shuntingyard.process;

import com.shuntingyard.pojo.evaluate.request.EvaluateRequest;
import com.shuntingyard.pojo.evaluate.response.EvaluateResponse;
import com.shuntingyard.services.evaluate.interfaces.IEvaluator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EvaluateProcess {
    private IEvaluator<EvaluateResponse> iEvaluator;
    private Logger log;

    public EvaluateProcess(IEvaluator iEvaluator){
        this.iEvaluator = iEvaluator;
        this.log =  LoggerFactory.getLogger(getClass());
    }

    public EvaluateResponse process(EvaluateRequest evaluateRequest){

        EvaluateResponse evaluateResponse = new EvaluateResponse();

        try {
            log.info("EvaluateProcess - Request: {}",evaluateRequest);
            evaluateResponse = iEvaluator.getEvaluateResponse(evaluateRequest);
            log.info("EvaluateProcess - Response: {}",evaluateResponse);

        }catch(Exception e){
            log.error("EvaluateProcess - error: {}",e.getMessage());
        }

        return evaluateResponse;

    }


}
