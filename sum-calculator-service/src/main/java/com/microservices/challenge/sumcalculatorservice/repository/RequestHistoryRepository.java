package com.microservices.challenge.sumcalculatorservice.repository;

import com.microservices.challenge.sumcalculatorservice.entity.RequestHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestHistoryRepository extends JpaRepository<RequestHistory, Long> {

    Page<RequestHistory> findAllByOrderByTimestampDesc(Pageable pageable);
}
