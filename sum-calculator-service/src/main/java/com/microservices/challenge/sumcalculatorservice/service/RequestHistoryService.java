package com.microservices.challenge.sumcalculatorservice.service;

import com.microservices.challenge.sumcalculatorservice.entity.RequestHistory;
import com.microservices.challenge.sumcalculatorservice.repository.RequestHistoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RequestHistoryService {

    private final RequestHistoryRepository requestHistoryRepository;

    public RequestHistoryService(RequestHistoryRepository requestHistoryRepository) {
        this.requestHistoryRepository = requestHistoryRepository;
    }

    public void save(RequestHistory requestHistory) {
        requestHistoryRepository.save(requestHistory);
    }

    public Page<RequestHistory> getAllHistory(Pageable pageable) {
        Page<RequestHistory> page =
                requestHistoryRepository.findAllByOrderByTimestampDesc(pageable);
        return page;
    }
}
