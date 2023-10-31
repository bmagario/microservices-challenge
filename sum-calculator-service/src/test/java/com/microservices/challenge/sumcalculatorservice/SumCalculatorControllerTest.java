package com.microservices.challenge.sumcalculatorservice;

import static org.mockito.Mockito.when;

import com.microservices.challenge.sumcalculatorservice.controller.SumCalculatorController;
import com.microservices.challenge.sumcalculatorservice.service.SumCalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
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

    @Mock
    private SumCalculatorService sumCalculatorService;

    @MockBean
    private SumCalculatorController sumCalculatorController;

    @BeforeEach
    public void init() {
        this.mockMvc =
                MockMvcBuilders.standaloneSetup(new SumCalculatorController(sumCalculatorService))
                        //.setControllerAdvice(new ErrorHandler())
                        .build();
    }

    @Test
    public void testCalculate() throws Exception {
        when(sumCalculatorService.performCalculation(3.0, 4.0)).thenReturn(7.0);


        mockMvc.perform(MockMvcRequestBuilders.get("/api/sum-calculator/calculate")
                        .param("num1", "3.0")
                        .param("num2", "4.0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("7.0"));
    }
}

