package com.microservices.challenge.sumcalculatorservice.repository;

import com.microservices.challenge.sumcalculatorservice.entity.CallHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CallHistoryRepository extends JpaRepository<CallHistory, Long> {

    Page<CallHistory> findAllByOrderByTimestampDesc(Pageable pageable);
}
