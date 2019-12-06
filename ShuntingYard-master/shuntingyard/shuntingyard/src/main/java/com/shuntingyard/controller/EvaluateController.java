package com.shuntingyard.controller;

import com.shuntingyard.pojo.evaluate.request.EvaluateRequest;
import com.shuntingyard.pojo.evaluate.response.EvaluateResponse;
import com.shuntingyard.process.EvaluateProcess;
import com.shuntingyard.utils.ServiceUtils;
import io.swagger.annotations.*;
import org.springframework.core.env.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Evaluate Infix Equations", description = "Obtiene el resultado final de una ecuacion infija, transformandola" +
        "a postfija y luego evaluando la ecuacion postfija para obtener el resultado final")
@PropertySource("classpath:configuration.properties")
@RequestMapping("${service.url}")
public class EvaluateController {

    private Logger log;
    private Environment env;
    private EvaluateProcess evaluateProcess;

    public EvaluateController(Environment env,
                              EvaluateProcess evaluateProcess){
        this.env = env;
        this.evaluateProcess = evaluateProcess;
        this.log =  LoggerFactory.getLogger(getClass());
    }

    @ApiOperation(value = "Obtiene la informacion de millas del cliente por medio del membershipNumber", response = EvaluateResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully", response = EvaluateResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = EvaluateResponse.class),
            @ApiResponse(code = 500, message = "Precondition failed", response = EvaluateResponse.class)
    })
    @PostMapping("${service.url.endpoint.evaluate}")
    public ResponseEntity<?> EvaluateInfix(
            @ApiParam(value = "Ecuacion infija a evaluar", required = true)@RequestBody EvaluateRequest evaluateRequest){

        ResponseEntity<?> processResponse;

        log.info("EvaluateController - Request: {}", evaluateRequest);
        processResponse = evaluateProcess.process(evaluateRequest);
        log.info("EvaluateController - Response: {}", ServiceUtils.objToString(processResponse.getBody()));
        return processResponse;

    }

}
