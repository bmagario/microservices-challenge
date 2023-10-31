package com.microservices.challenge.sumcalculatorservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
class SumCalculatorServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private SumCalculatorService sumCalculatorService;

    @Test
    void performCalculationShouldReturnCorrectResult() {
        Double num1 = 5.0;
        Double num2 = 5.0;
        Double mockPercentage = 5.0;

        when(restTemplate.getForObject(any(String.class), any(Class.class)))
                .thenReturn(mockPercentage);

        Double result = sumCalculatorService.performCalculation(num1, num2);

        Double expected = num1 + num2 + (num1 + num2) * mockPercentage / 100;
        assertEquals(expected, result, 0.001);
    }
}
