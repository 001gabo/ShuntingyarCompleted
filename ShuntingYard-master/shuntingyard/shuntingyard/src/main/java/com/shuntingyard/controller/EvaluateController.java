package com.shuntingyard.controller;

import com.shuntingyard.pojo.evaluate.request.EvaluateRequest;
import com.shuntingyard.pojo.evaluate.response.EvaluateResponse;
import com.shuntingyard.process.EvaluateProcess;
import com.shuntingyard.utils.ValidateEquation;
import org.springframework.core.env.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PropertySource("classpath:configuration.properties")
@RequestMapping("${service.url}")
public class EvaluateController {

    private Logger log;
    private Environment env;
    private EvaluateProcess evaluateProcess;
    private ValidateEquation validateEquation;

    public EvaluateController(Environment env,
                              EvaluateProcess evaluateProcess,
                              ValidateEquation validateEquation){
        this.env = env;
        this.validateEquation = validateEquation;
        this.evaluateProcess = evaluateProcess;
        this.log =  LoggerFactory.getLogger(getClass());
    }

    @PostMapping("${service.url.endpoint.evaluate}")
    public ResponseEntity<?> EvaluateInfix(@RequestBody EvaluateRequest evaluateRequest){

        EvaluateResponse evaluateResponse = new EvaluateResponse();

        log.info("EvaluateController - Request: {}", evaluateRequest);
        switch (validateEquation.ValidateEquation(evaluateRequest)){
            case 1:
                evaluateResponse = evaluateProcess.process(evaluateRequest);
                log.info("EvaluateController - Response: {}", evaluateResponse);
                return new ResponseEntity<>(evaluateResponse,HttpStatus.OK);
            case 2:
                return new ResponseEntity<>(evaluateResponse,HttpStatus.BAD_REQUEST);
        }


        return null;
    }

}
