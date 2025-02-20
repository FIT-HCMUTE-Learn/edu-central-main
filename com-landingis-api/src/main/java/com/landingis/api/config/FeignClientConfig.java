package com.landingis.api.config;

import com.landingis.api.service.TokenService;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;

import java.util.Base64;
import java.util.stream.Collectors;

@Configuration
public class FeignClientConfig {

    @Autowired
    private TokenService tokenService;

    @Bean
    public RequestInterceptor bearerAuthRequestInterceptor() {
        return requestTemplate -> {
            if (requestTemplate.url().contains("/api/auth/token")) {
                return;
            }

            String token = tokenService.getToken();
            requestTemplate.header("Authorization", "Bearer " + token);
        };
    }

    @Bean
    public RequestInterceptor pageableInterceptor() {
        return template -> {
            if (template.method().equals("GET") && template.requestBody() == null) {

                Pageable pageable = (Pageable) template.request().requestTemplate().queries().get("pageable");

                if (pageable != null) {
                    template.query("page", String.valueOf(pageable.getPageNumber()));
                    template.query("size", String.valueOf(pageable.getPageSize()));

                    if (pageable.getSort().isSorted()) {
                        String sortParam = pageable.getSort().stream()
                                .map(order -> order.getProperty() + "," + order.getDirection().name().toLowerCase())
                                .collect(Collectors.joining("&sort="));
                        template.query("sort", sortParam);
                    }
                }
            }
        };
    }
}
