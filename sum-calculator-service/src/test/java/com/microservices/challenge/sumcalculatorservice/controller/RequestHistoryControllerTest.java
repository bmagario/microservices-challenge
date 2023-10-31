package com.microservices.challenge.sumcalculatorservice.controller;

import static org.mockito.Mockito.when;

import com.microservices.challenge.sumcalculatorservice.entity.RequestHistory;
import com.microservices.challenge.sumcalculatorservice.service.RequestHistoryService;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@WebMvcTest(RequestHistoryController.class)
@ExtendWith(MockitoExtension.class)
class RequestHistoryControllerTest extends BaseControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @Mock
    private RequestHistoryService requestHistoryService;

    @MockBean
    private RequestHistoryController requestHistoryController;

    @BeforeEach
    public void init() {
        this.mockMvc =
                MockMvcBuilders.standaloneSetup(new RequestHistoryController(requestHistoryService))
                        //.setControllerAdvice(new ErrorHandler())
                        .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                        .build();
    }

    @Test
    public void testGetHistory() throws Exception {
        Pageable pageableFilter = PageRequest.of(0, 5, Sort.unsorted());
        List<RequestHistory> requestHistoryList = createMockRequestHistory();
        Page<RequestHistory> mockPage =
                new PageImpl<>(requestHistoryList, pageableFilter, requestHistoryList.size());

        when(requestHistoryService.getAllHistory(
                PageRequest.of(0, 5, Sort.by("timestamp").descending())))
                .thenReturn(mockPage);

        String uri = "/api/request-history/all?page=0&size=5";

        mockMvc.perform(MockMvcRequestBuilders.get(uri)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.size()").value(5));
    }

    private List<RequestHistory> createMockRequestHistory() {
        return Arrays.asList(
                new RequestHistory(31L, "/api/sum-calculator/calculate", "GET", "test request", 200,
                        "Test response", LocalDateTime.parse("2023-10-31T13:09:36.801905")),
                new RequestHistory(30L, "/api/sum-calculator/calculate", "GET", "test request", 200,
                        "Test response", LocalDateTime.parse("2023-10-31T13:09:35.213288")),
                new RequestHistory(29L, "/api/sum-calculator/calculate", "GET", "test request", 200,
                        "Test response", LocalDateTime.parse("2023-10-31T13:09:18.11473")),
                new RequestHistory(28L, "/api/sum-calculator/calculate", "GET", "test request", 200,
                        "Test response", LocalDateTime.parse("2023-10-31T13:09:17.308171")),
                new RequestHistory(27L, "/api/sum-calculator/calculate", "GET", "test request", 200,
                        "Test response", LocalDateTime.parse("2023-10-31T13:09:15.174292"))
        );
    }
}

