package com.ruansegrini.clinica_api.infra.security;

import com.ruansegrini.clinica_api.domain.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {
    @Value("${api.security.token.secret}")
    private String secret;
    @Value("${api.security.token.expiration}")
    private long expiration;

    public String generateToken(User user) {
        return Jwts.builder()
                .subject(user.getLogin())
                .claim("role", user.getRole().name())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey())
                .compact();
    }

    public String extractLogin(String token) {
        return getClaims(token).getSubject();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String login = extractLogin(token);
        return login.equals(userDetails.getUsername()) && !isExpired(token);
    }
    private boolean isExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }
    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
}