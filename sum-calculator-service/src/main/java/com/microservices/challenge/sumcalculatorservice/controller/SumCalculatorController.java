package com.microservices.challenge.sumcalculatorservice.controller;

import com.microservices.challenge.sumcalculatorservice.service.SumCalculatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sum-calculator")
public class SumCalculatorController {

    private final SumCalculatorService sumCalculatorService;

    public SumCalculatorController(SumCalculatorService sumCalculatorService) {
        this.sumCalculatorService = sumCalculatorService;
    }

    @GetMapping("/calculate")
    public ResponseEntity<Double> calculate(@RequestParam(name = "num1") Double num1,
                                            @RequestParam(name = "num2") Double num2) {
        Double percentage = sumCalculatorService.getPercentageValue();
        Double result =
                sumCalculatorService.performCalculation(num1, num2, percentage);
        return ResponseEntity.ok(result);
    }
}
