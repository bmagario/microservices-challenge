package com.microservices.challenge.sumcalculatorservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.microservices.challenge.sumcalculatorservice.exception.PercentageServiceUnavailableException;
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
    void performCalculation_WhenGetPercentageFromExternalService_ShouldReturnCorrectResult() {
        Double num1 = 5.0;
        Double num2 = 5.0;
        Double percentage = 5.0;

        Double result = sumCalculatorService.performCalculation(num1, num2, percentage);
        Double expected = num1 + num2 + (num1 + num2) * percentage / 100;
        assertEquals(expected, result, 0.001);
    }

    @Test
    void getPercentageValue_WhenGetPercentageFromExternalService_ShouldReturnCorrectResult() {
        Double percentage = 5.0;

        when(restTemplate.getForObject(any(String.class), any(Class.class))).thenReturn(percentage);

        Double percentageValue = sumCalculatorService.getPercentageValue();
        assertEquals(percentage, percentageValue, 0.001);
    }

    @Test
    void fallbackPercentage_WhenLastSuccessfulResultIsNull_ShouldThrowsPercentageServiceUnavailableException() {
        Exception mockException = mock(Exception.class);
        assertThrows(PercentageServiceUnavailableException.class, () -> {
            sumCalculatorService.fallbackPercentage(mockException);
        });
    }

    @Test
    void fallbackPercentage_WhenLastSuccessfulResultIsPresent_ShouldReturnLastSuccessfulResultIsPresent() {
        Exception mockException = mock(Exception.class);
        sumCalculatorService.setLastSuccessfulPercentage(11.0);

        Double fallbackResult = sumCalculatorService.fallbackPercentage(mockException);

        assertEquals(11.0, fallbackResult);
    }

}
