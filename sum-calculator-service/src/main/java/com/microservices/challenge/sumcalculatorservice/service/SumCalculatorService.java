package com.microservices.challenge.sumcalculatorservice.service;

import com.microservices.challenge.sumcalculatorservice.exception.PercentageServiceUnavailableException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SumCalculatorService {
    private final RestTemplate restTemplate;

    public void setLastSuccessfulResult(Double lastSuccessfulResult) {
        this.lastSuccessfulResult = lastSuccessfulResult;
    }

    private Double lastSuccessfulResult;


    public SumCalculatorService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Retry(name = "percentageService", fallbackMethod = "fallbackPercentage")
    @CircuitBreaker(name = "percentageService", fallbackMethod = "fallbackPercentage")
    @Cacheable(value = "percentageCache", key = "'percentage'", unless = "#result == null")
    public Double performCalculation(Double num1, Double num2) {
        Double percentage = restTemplate.getForObject(
                "http://percentage-service/api/percentage",
                Double.class);
        Double result = num1 + num2 + (num1 + num2) * percentage / 100;
        lastSuccessfulResult = result;

        return result;
    }

    public Double fallbackPercentage(Exception e) {
        if (lastSuccessfulResult == null) {
            throw new PercentageServiceUnavailableException(
                    "There was a problem getting the percentage value!");
        }
        return lastSuccessfulResult;
    }
}
