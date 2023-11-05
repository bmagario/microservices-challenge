package com.microservices.challenge.sumcalculatorservice.config;

import com.microservices.challenge.sumcalculatorservice.interceptor.RateLimitInterceptor;
import com.microservices.challenge.sumcalculatorservice.interceptor.RequestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final RequestInterceptor requestInterceptor;
    private final RateLimitInterceptor rateLimitInterceptor;

    public WebMvcConfig(RequestInterceptor requestInterceptor,
                        RateLimitInterceptor rateLimitInterceptor) {
        this.requestInterceptor = requestInterceptor;
        this.rateLimitInterceptor = rateLimitInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(rateLimitInterceptor);
        registry.addInterceptor(requestInterceptor);
    }
}
