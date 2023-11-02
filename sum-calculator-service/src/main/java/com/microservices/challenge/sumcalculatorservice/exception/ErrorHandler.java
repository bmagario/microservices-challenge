package com.microservices.challenge.sumcalculatorservice.exception;

import static java.util.Objects.isNull;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    private static final String GENERIC = "0000";
    private static final String TOO_MANY_REQUESTS = "0429";
    private static final String SERVICE_UNAVAILABLE = "0503";

    @ExceptionHandler(Exception.class)
    private ResponseEntity<ErrorResponse> handleGenericException(Throwable exception) {
        log.error("Generic error - Exception: ", exception);
        return new ResponseEntity<>(ErrorResponse.builder()
                .errors(!isNull(exception.getMessage()) ?
                        List.of(exception.getMessage()).toString() : exception.toString())
                .code(GENERIC)
                .message("Something went wrong!")
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpClientErrorException.TooManyRequests.class)
    private ResponseEntity<ErrorResponse> handleHttpClientErrorExceptionTooManyRequests(
            HttpClientErrorException.TooManyRequests exception) {
        log.error("HttpClientErrorException.TooManyRequests: ", exception);
        return new ResponseEntity<>(ErrorResponse.builder()
                .errors(!isNull(exception.getMessage()) ?
                        List.of(exception.getMessage()).toString() : exception.toString())
                .code(TOO_MANY_REQUESTS)
                .message("Too Many Requests! Please try later in a minute!")
                .build(), HttpStatus.TOO_MANY_REQUESTS);
    }

    @ExceptionHandler(PercentageServiceUnavailableException.class)
    private ResponseEntity<ErrorResponse> handlePercentageServiceUnavailableException(
            PercentageServiceUnavailableException exception) {
        log.error("PercentageServiceUnavailableException: ", exception);
        return new ResponseEntity<>(ErrorResponse.builder()
                .errors(!isNull(exception.getMessage()) ?
                        List.of(exception.getMessage()).toString() : exception.toString())
                .code(SERVICE_UNAVAILABLE)
                .message("Percentage service not available! Please wait a minute and try again!")
                .build(), HttpStatus.SERVICE_UNAVAILABLE);
    }
}
