package com.landingis.api.config;

import com.landingis.api.constant.FeignConstant;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;

import java.util.Base64;
import java.util.stream.Collectors;

@Configuration
public class FeignClientConfig {

    @Bean
    public RequestInterceptor basicAuthRequestInterceptor() {
        return requestTemplate -> {
            String auth = FeignConstant.BASIC_AUTH_USERNAME + ":" + FeignConstant.BASIC_AUTH_PASSWORD;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
            requestTemplate.header("Authorization", "Basic " + encodedAuth);
        };
    }

    @Bean
    public RequestInterceptor pageableInterceptor() {
        return new RequestInterceptor() {

            @Override
            public void apply(RequestTemplate template) {
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
            }
        };
    }
}
