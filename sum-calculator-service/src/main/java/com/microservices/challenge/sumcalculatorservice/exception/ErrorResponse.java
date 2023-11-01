package com.microservices.challenge.sumcalculatorservice.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    @JsonProperty(value = "error_code")
    private String code;

    @JsonProperty(value = "errors")
    private String errors;

    @JsonProperty(value = "message")
    private String message;
}