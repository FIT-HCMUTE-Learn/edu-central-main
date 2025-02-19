package com.landingis.api.util;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class FeignUtils {

    public String getBasicAuthHeader() {
        String username = "username";
        String password = "123456";
        String credentials = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());
    }
}

