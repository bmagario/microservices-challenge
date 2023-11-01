package com.microservices.challenge.sumcalculatorservice.exception;

public class PercentageServiceUnavailableException extends RuntimeException {
    public PercentageServiceUnavailableException(String message) {
        super(message);
    }
}
