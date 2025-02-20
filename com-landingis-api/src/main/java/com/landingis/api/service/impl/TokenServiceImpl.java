package com.landingis.api.service.impl;

import com.landingis.api.client.AuthenticationClient;
import com.landingis.api.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private AuthenticationClient authenticationClient;

    private String token = null;
    private Date expirationTime = null;

    @Value("${jwt.secret}")
    private String secretKey;

    @Override
    public String getToken() {
        if (token == null || isTokenExpired()) {
            refreshToken();
        }
        return token;
    }

    @Override
    public synchronized void refreshToken() {
        this.token = authenticationClient.generateToken().getToken();
        this.expirationTime = extractExpiration(token);
    }

    private boolean isTokenExpired() {
        return expirationTime == null || expirationTime.before(new Date());
    }

    private Date extractExpiration(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        return claims.getExpiration();
    }
}
