package com.microservices.challenge.sumcalculatorservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CalculationRequestDTO {
    private Double num1;
    private Double num2;
}

