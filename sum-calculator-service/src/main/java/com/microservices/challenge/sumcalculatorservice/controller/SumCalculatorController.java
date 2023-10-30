package com.microservices.challenge.sumcalculatorservice.controller;

import com.microservices.challenge.sumcalculatorservice.dto.CalculationRequestDTO;
import com.microservices.challenge.sumcalculatorservice.service.SumCalculatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sum-calculator")
public class SumCalculatorController {

    private final SumCalculatorService sumCalculatorService;

    public SumCalculatorController(SumCalculatorService sumCalculatorService) {
        this.sumCalculatorService = sumCalculatorService;
    }

    @GetMapping("/calculate")
    public ResponseEntity<Double> calculate(@RequestBody CalculationRequestDTO requestDTO) {
        Double result =
                sumCalculatorService.performCalculation(requestDTO.getNum1(), requestDTO.getNum2());
        return ResponseEntity.ok(result);
    }
}
