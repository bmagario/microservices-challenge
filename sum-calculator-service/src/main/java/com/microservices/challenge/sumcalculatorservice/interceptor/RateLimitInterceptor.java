package com.microservices.challenge.sumcalculatorservice.interceptor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    private static final int MAX_REQUESTS_PER_MINUTE = 3;
    private final Map<String, Map<Long, AtomicInteger>> userRequestCounts =
            new ConcurrentHashMap<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        String ipAddress = getClientIP(request);

        Map<Long, AtomicInteger> requestCounts =
                userRequestCounts.computeIfAbsent(ipAddress, k -> new ConcurrentHashMap<>());

        long currentMinute = System.currentTimeMillis() / (60 * 1000);

        AtomicInteger requestCount =
                requestCounts.computeIfAbsent(currentMinute, k -> new AtomicInteger(0));

        if (requestCount.incrementAndGet() > MAX_REQUESTS_PER_MINUTE) {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.getWriter().write("Too many requests. Please try again later.");
            return false;
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        String ipAddress = getClientIP(request);
        userRequestCounts.computeIfPresent(ipAddress, (k, v) -> {
            v.entrySet()
                    .removeIf(entry -> entry.getKey() < System.currentTimeMillis() / (60 * 1000));
            return v;
        });
    }

    private String getClientIP(HttpServletRequest request) {
        String xForwardedForHeader = request.getHeader("X-Forwarded-For");
        if (xForwardedForHeader != null && !xForwardedForHeader.isEmpty()) {
            return xForwardedForHeader.split(",")[0].trim();
        }
        return request.getRemoteAddr();
    }
}
