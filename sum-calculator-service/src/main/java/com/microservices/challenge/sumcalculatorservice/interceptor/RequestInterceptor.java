package com.microservices.challenge.sumcalculatorservice.interceptor;

import com.microservices.challenge.sumcalculatorservice.entity.RequestHistory;
import com.microservices.challenge.sumcalculatorservice.service.RequestHistoryService;
import java.io.IOException;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class RequestInterceptor implements HandlerInterceptor {

    private final RequestHistoryService requestHistoryService;

    public RequestInterceptor(RequestHistoryService requestHistoryService) {
        this.requestHistoryService = requestHistoryService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws IOException {
        if ("/api/request-history/all".equals(request.getRequestURI())) {
            return true; // Skip interceptor logic for this URL
        }
        
        RequestHistory history = new RequestHistory();
        history.setEndpoint(request.getRequestURI());
        history.setMethod(request.getMethod());
        history.setQueryString(request.getQueryString());
        history.setRequestBody(getRequestBody(request));
        history.setTimestamp(LocalDateTime.now());

        request.setAttribute("requestHistory", history);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {
        RequestHistory history = (RequestHistory) request.getAttribute("requestHistory");
        if (history != null) {
            history.setStatusCode(response.getStatus());
            history.setResponseBody(getResponseBody(response));
            requestHistoryService.save(history);
        }
    }

    private String getRequestBody(HttpServletRequest request) throws IOException {
        return RequestBodyReader.readRequestBody(request);
    }

    private String getResponseBody(HttpServletResponse response) {
        return "Test response";
    }
}

