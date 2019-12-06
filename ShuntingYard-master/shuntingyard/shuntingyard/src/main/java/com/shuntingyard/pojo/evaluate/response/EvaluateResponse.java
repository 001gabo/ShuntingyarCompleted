
package com.shuntingyard.pojo.evaluate.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.ALWAYS)
@JsonPropertyOrder({
    "infix",
    "postfix",
    "result",
    "message"
})
public class EvaluateResponse {

    @JsonProperty("infix")
    @ApiModelProperty( value = "Ecuacion infija", example = "1+2.5/3*4")
    private String infix;
    @JsonProperty("postfix")
    @ApiModelProperty( value = "Ecuacion postfija", example = "1+2.5/3*4")
    private String postfix;
    @JsonProperty("result")
    @ApiModelProperty( value = "Resultado final de la evaluacion de la ecuacion postfija", example = "4.333333333333334")
    private Double result;
    @JsonProperty("message")
    @ApiModelProperty( value = "Mensaje de la evaluacion de la ecuacion postfija", example = "Success")
    private String message;

    @JsonProperty("infix")
    public String getInfix() {
        return infix;
    }

    @JsonProperty("infix")
    public void setInfix(String infix) {
        this.infix = infix;
    }

    @JsonProperty("postfix")
    public String getPostfix() {
        return postfix;
    }

    @JsonProperty("postfix")
    public void setPostfix(String postfix) {
        this.postfix = postfix;
    }

    @JsonProperty("result")
    public Double getResult() {
        return result;
    }

    @JsonProperty("result")
    public void setResult(Double result) {
        this.result = result;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

}
