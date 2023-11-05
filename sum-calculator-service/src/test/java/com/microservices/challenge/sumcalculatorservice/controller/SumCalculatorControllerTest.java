package com.microservices.challenge.sumcalculatorservice.controller;

import com.microservices.challenge.sumcalculatorservice.exception.ErrorHandler;
import com.microservices.challenge.sumcalculatorservice.service.SumCalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@WebMvcTest(SumCalculatorController.class)
@ExtendWith(MockitoExtension.class)
class SumCalculatorControllerTest extends BaseControllerTest {
    private MockMvc mockMvc;

    @MockBean
    private SumCalculatorService sumCalculatorService;

    @BeforeEach
    public void setUp() {
        this.mockMvc =
                MockMvcBuilders.standaloneSetup(new SumCalculatorController(sumCalculatorService))
                        .setControllerAdvice(ErrorHandler.class)
                        .build();
    }


    @Test
    void calculate_WhenParamsOk_ShouldReturnCorrectValue() throws Exception {
        Double percentage = 1.0;
        Double num1 = 3.0;
        Double num2 = 4.0;
        Double result = num1 + num2 + (num1 + num2) * percentage / 100;
        Mockito.when(sumCalculatorService.getPercentageValue()).thenReturn(percentage);
        Mockito.when(sumCalculatorService.performCalculation(num1, num2, percentage))
                .thenReturn(result);


        mockMvc.perform(MockMvcRequestBuilders.get("/api/sum-calculator/calculate")
                        .param("num1", num1.toString())
                        .param("num2", num2.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(result.toString()));
    }

    @Test
    void calculate_WhenNumParamsIncorrect_ShouldReturnBadRequest() throws Exception {
        String num1 = "3.0";
        String num2 = "invalid_number";

        mockMvc.perform(MockMvcRequestBuilders.get("/api/sum-calculator/calculate")
                        .param("num1", num1)
                        .param("num2", num2)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
