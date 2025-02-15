package com.landingis.api.util;

import com.landingis.api.security.CustomUserDetails;
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

    public String generateToken(CustomUserDetails userDetails, List<String> pcodes) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("userId", userDetails.getUserId())
                .claim("group", userDetails.getGroup())
                .claim("kind", userDetails.getKind())
                .claim("isSuperAdmin", userDetails.getIsSuperAdmin())
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

    public Boolean extractIsSuperAdmin(String token) {
        return extractClaim(token, claims -> claims.get("isSuperAdmin", Boolean.class));
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
