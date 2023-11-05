package com.microservices.challenge.sumcalculatorservice.controller;

import com.microservices.challenge.sumcalculatorservice.service.SumCalculatorService;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/sum-calculator")
public class SumCalculatorController {

    private static final String VALIDATION_REGEX = "^\\d+(\\.\\d+)?(,\\d+(\\.\\d+)?)*$";

    private final SumCalculatorService sumCalculatorService;

    public SumCalculatorController(SumCalculatorService sumCalculatorService) {
        this.sumCalculatorService = sumCalculatorService;
    }

    @GetMapping("/calculate")
    public ResponseEntity<Double> calculate(
            @RequestParam("num1")
            @Valid @Pattern(regexp = VALIDATION_REGEX)
            String num1,
            @RequestParam("num2")
            @Valid @Pattern(regexp = VALIDATION_REGEX)
            String num2) {
        Double parsedNum1 = Double.valueOf(num1);
        Double parsedNum2 = Double.valueOf(num2);

        Double percentage = sumCalculatorService.getPercentageValue();
        Double result = sumCalculatorService.performCalculation(parsedNum1, parsedNum2, percentage);
        return ResponseEntity.ok(result);
    }

}
