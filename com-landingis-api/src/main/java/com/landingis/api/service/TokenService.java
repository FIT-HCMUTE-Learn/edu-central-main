package com.landingis.api.service;

public interface TokenService {
    String getToken();
    void refreshToken();
}
