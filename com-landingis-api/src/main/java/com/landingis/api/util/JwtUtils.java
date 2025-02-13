package com.landingis.api.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.function.Function;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expirationTime;

    public String generateToken(Long userId, String username, String group, Integer kind, List<String> pcodes) {
        return Jwts.builder()
                .setSubject(username)
                .claim("userId", userId)
                .claim("group", group)
                .claim("kind", kind)
                .claim("pcodes", pcodes)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Long extractUserId(String token) {
        return extractClaim(token, claims -> claims.get("userId", Long.class));
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractGroup(String token) {
        return extractClaim(token, claims -> claims.get("group", String.class));
    }

    public Integer extractKind(String token) {
        return extractClaim(token, claims -> claims.get("kind", Integer.class));
    }

    public List<String> extractPcodes(String token) {
        return extractClaim(token, claims -> claims.get("pcodes", List.class));
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }
}
