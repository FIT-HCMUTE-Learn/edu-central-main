package com.landingis.api.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.Collection;

@Configuration
public class FeignClientConfig {

    @Bean
    public RequestInterceptor pageableInterceptor() {
        return requestTemplate -> {
            // Get token from SecurityContext
            String token = null;
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null) {
                token = (String) authentication.getCredentials();
            }
            requestTemplate.header("Authorization", "Bearer " + token);

            // Process for Pageable form Header
            Collection<String> pageableHeaders = requestTemplate.headers().get("CUSTOM_PAGEABLE");
            if (pageableHeaders != null && !pageableHeaders.isEmpty()) {
                String pageableParam = pageableHeaders.iterator().next();

                Arrays.stream(pageableParam.split("&"))
                        .map(param -> param.split("="))
                        .forEach(pair -> requestTemplate.query(pair[0], pair[1]));

                requestTemplate.header("CUSTOM_PAGEABLE", (String) null);
            }
        };
    }
}
