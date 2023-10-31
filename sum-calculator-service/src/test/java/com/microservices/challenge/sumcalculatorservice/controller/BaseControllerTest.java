package com.microservices.challenge.sumcalculatorservice.controller;

import com.microservices.challenge.sumcalculatorservice.interceptor.RequestInterceptor;
import org.springframework.boot.test.mock.mockito.MockBean;

public class BaseControllerTest {
    @MockBean
    RequestInterceptor requestInterceptor;
}
