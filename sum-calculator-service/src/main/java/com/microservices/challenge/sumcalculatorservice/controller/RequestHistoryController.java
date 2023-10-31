package com.microservices.challenge.sumcalculatorservice.controller;

import com.microservices.challenge.sumcalculatorservice.entity.RequestHistory;
import com.microservices.challenge.sumcalculatorservice.service.RequestHistoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/request-history")
public class RequestHistoryController {

    private final RequestHistoryService requestHistoryService;

    public RequestHistoryController(RequestHistoryService requestHistoryService) {
        this.requestHistoryService = requestHistoryService;
    }

    @GetMapping("/all")
    public ResponseEntity<Page<RequestHistory>> getHistory(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable =
                PageRequest.of(page, size, Sort.by("timestamp").descending());
        Page<RequestHistory> history = requestHistoryService.getAllHistory(pageable);
        return ResponseEntity.ok(history);
    }
}
