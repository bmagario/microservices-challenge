package com.microservices.challenge.percentageservice.service;

import java.util.Random;
import org.springframework.stereotype.Service;

@Service
public class PercentageService {

    public Double getPercentage() {
        return generateRandomPercentage();
    }

    private Double generateRandomPercentage() {
        Random random = new Random();
        return new Double(random.nextInt(20) + 1);
    }
}
