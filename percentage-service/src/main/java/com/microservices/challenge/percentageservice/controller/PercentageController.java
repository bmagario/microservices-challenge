package com.microservices.challenge.percentageservice.controller;

import com.microservices.challenge.percentageservice.service.PercentageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/percentage")
public class PercentageController {

    private final PercentageService percentageService;

    public PercentageController(PercentageService percentageService) {
        this.percentageService = percentageService;
    }

    @GetMapping()
    public ResponseEntity<Double> getPercentage() {
        Double percentage = percentageService.getPercentage();
        return ResponseEntity.ok(percentage);
    }

}
