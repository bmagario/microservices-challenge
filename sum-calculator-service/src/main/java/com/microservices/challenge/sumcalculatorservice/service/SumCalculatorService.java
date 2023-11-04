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

    private Double lastSuccessfulPercentage;


    public SumCalculatorService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Double performCalculation(Double num1, Double num2, Double percentage) {
        lastSuccessfulPercentage = percentage;

        return num1 + num2 + (num1 + num2) * lastSuccessfulPercentage / 100;
    }

    @Retry(name = "percentageService", fallbackMethod = "fallbackPercentage")
    @CircuitBreaker(name = "percentageService", fallbackMethod = "fallbackPercentage")
    @Cacheable(value = "percentageCache", key = "'percentage'", unless = "#result == null")
    public Double getPercentageValue() {
        return restTemplate.getForObject(
                "http://percentage-service/api/percentage",
                Double.class);
    }

    public Double fallbackPercentage(Exception e) {
        if (lastSuccessfulPercentage == null) {
            throw new PercentageServiceUnavailableException(
                    "There was a problem getting the percentage value!");
        }
        return lastSuccessfulPercentage;
    }

    public void setLastSuccessfulPercentage(Double lastSuccessfulPercentage) {
        this.lastSuccessfulPercentage = lastSuccessfulPercentage;
    }
}
