package com.microservices.challenge.sumcalculatorservice.controller;

import com.microservices.challenge.sumcalculatorservice.dto.CalculationRequestDTO;
import com.microservices.challenge.sumcalculatorservice.entity.RequestHistory;
import com.microservices.challenge.sumcalculatorservice.service.RequestHistoryService;
import com.microservices.challenge.sumcalculatorservice.service.SumCalculatorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sum-calculator")
public class SumCalculatorController {

    private final SumCalculatorService sumCalculatorService;
    private final RequestHistoryService requestHistoryService;

    public SumCalculatorController(SumCalculatorService sumCalculatorService,
                                   RequestHistoryService requestHistoryService) {
        this.sumCalculatorService = sumCalculatorService;
        this.requestHistoryService = requestHistoryService;
    }

    // @CircuitBreaker(name = "percentageCB", fallbackMethod = "fallBackGetPercentage")
    @GetMapping("/calculate")
    public ResponseEntity<Double> calculate(@RequestBody CalculationRequestDTO requestDTO) {
        Double result =
                sumCalculatorService.performCalculation(requestDTO.getNum1(), requestDTO.getNum2());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/history")
    public ResponseEntity<Page<RequestHistory>> getHistory(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10")
            int size) {
        Pageable pageable =
                PageRequest.of(page, size, Sort.by("timestamp").descending());
        Page<RequestHistory> history = requestHistoryService.getAllHistory(pageable);
        return ResponseEntity.ok(history);
    }
}
