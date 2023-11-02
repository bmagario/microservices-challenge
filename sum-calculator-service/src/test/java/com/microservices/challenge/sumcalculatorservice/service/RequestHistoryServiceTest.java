package com.microservices.challenge.sumcalculatorservice.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.microservices.challenge.sumcalculatorservice.entity.RequestHistory;
import com.microservices.challenge.sumcalculatorservice.repository.RequestHistoryRepository;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@ExtendWith(MockitoExtension.class)
public class RequestHistoryServiceTest {

    @Mock
    private RequestHistoryRepository requestHistoryRepository;

    @InjectMocks
    private RequestHistoryService requestHistoryService;

    @Test
    public void testSaveRequestHistory() {
        RequestHistory requestHistory = new RequestHistory();
        requestHistoryService.saveRequestHistory(requestHistory);
        verify(requestHistoryRepository).save(requestHistory);
    }

    @Test
    void getAllHistory_WhenFindAllByOrderByTimestampDesc_ShouldReturnPageableResult() {
        Pageable pageable = PageRequest.of(0, 5, Sort.unsorted());
        List<RequestHistory> requestHistoryList = createMockRequestHistory();
        Page<RequestHistory> mockPage =
                new PageImpl<>(requestHistoryList, pageable, requestHistoryList.size());

        when(requestHistoryRepository.findAllByOrderByTimestampDesc(pageable)).thenReturn(
                mockPage);
        Page<RequestHistory> resultPage = requestHistoryService.getAllHistory(pageable);

        Assertions.assertEquals(mockPage, resultPage);
    }

    private List<RequestHistory> createMockRequestHistory() {
        return Arrays.asList(
                new RequestHistory(31L, "/api/sum-calculator/calculate", "GET",
                        "test request", "num1=5&num2=5", 200,
                        "Test response", LocalDateTime.parse("2023-10-31T13:09:36.801905")),
                new RequestHistory(30L, "/api/sum-calculator/calculate", "GET",
                        "test request", "num1=5&num2=5", 200,
                        "Test response", LocalDateTime.parse("2023-10-31T13:09:35.213288")),
                new RequestHistory(29L, "/api/sum-calculator/calculate", "GET",
                        "test request", "num1=5&num2=5", 200,
                        "Test response", LocalDateTime.parse("2023-10-31T13:09:18.11473")),
                new RequestHistory(28L, "/api/sum-calculator/calculate", "GET",
                        "test request", "num1=5&num2=5", 200,
                        "Test response", LocalDateTime.parse("2023-10-31T13:09:17.308171")),
                new RequestHistory(27L, "/api/sum-calculator/calculate", "GET",
                        "test request", "num1=5&num2=5", 200,
                        "Test response", LocalDateTime.parse("2023-10-31T13:09:15.174292"))
        );
    }
}
