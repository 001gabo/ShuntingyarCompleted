package com.shuntingyard.process;

import com.shuntingyard.pojo.evaluate.request.EvaluateRequest;
import com.shuntingyard.pojo.evaluate.response.EvaluateResponse;
import com.shuntingyard.services.evaluate.interfaces.IEvaluator;
import com.shuntingyard.utils.ValidateEquation;
import org.slf4j.Logger;
import org.springframework.core.env.Environment;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EvaluateProcess {
    private IEvaluator<EvaluateResponse> iEvaluator;
    private Logger log;
    private Environment env;
    private ValidateEquation validateEquation;

    public EvaluateProcess(IEvaluator iEvaluator,Environment env,ValidateEquation validateEquation){
        this.env = env;
        this.iEvaluator = iEvaluator;
        this.validateEquation = validateEquation;
        this.log =  LoggerFactory.getLogger(getClass());
    }

    public ResponseEntity<?> process(EvaluateRequest evaluateRequest){

        EvaluateResponse evaluateResponse = new EvaluateResponse();

        try {
            switch (validateEquation.ValidateEquation(evaluateRequest)) {
                case 1:
                    log.info("EvaluateProcess - Request: {}", evaluateRequest);
                    evaluateResponse = iEvaluator.getEvaluateResponse(evaluateRequest);
                    log.info("EvaluateProcess - Response: {}", evaluateResponse);

                    if (evaluateResponse.getMessage().equals("Success")) {
                        return new ResponseEntity<>(evaluateResponse, HttpStatus.OK);
                    } else if (evaluateResponse.getMessage().equals(env.getProperty("service.response.internal.error"))) {
                        return new ResponseEntity<>(evaluateResponse, HttpStatus.INTERNAL_SERVER_ERROR);
                    }

                case 2:
                    log.info(env.getProperty("service.response.bad.request"),evaluateRequest.getExp());
                    evaluateResponse.setMessage(env.getProperty("service.response.bad.request").replace("{}",evaluateRequest.getExp()));
                    return new ResponseEntity<>(evaluateResponse, HttpStatus.BAD_REQUEST);

                default:
                    evaluateResponse.setMessage(env.getProperty("service.response.internal.error"));
                    return new ResponseEntity<>(evaluateResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }catch(Exception e){
            log.error("EvaluateProcess - error: {}",e.getMessage());
            return new ResponseEntity<>(evaluateResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
