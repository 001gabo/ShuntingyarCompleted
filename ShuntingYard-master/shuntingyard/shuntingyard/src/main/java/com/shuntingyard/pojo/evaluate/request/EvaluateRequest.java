
package com.shuntingyard.pojo.evaluate.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.ALWAYS)
@JsonPropertyOrder({
    "exp"
})
public class EvaluateRequest {

    @JsonProperty("exp")
    @ApiModelProperty( value = "Ecuacion infija a evaluar", example = "2/1+3")
    private String exp;

    @JsonProperty("exp")
    public String getExp() {
        return exp;
    }

    @JsonProperty("exp")
    public void setExp(String exp) {
        this.exp = exp;
    }

}
