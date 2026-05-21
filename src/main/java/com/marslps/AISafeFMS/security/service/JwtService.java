package com.marslps.AISafeFMS.security.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.util.function.Function;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String jwt_secret;
    @Value("${jwt.expiration}")
    private Long jwt_expiration;

    private SecretKey getSignInKey() {
        return Keys.hmacShaKeyFor(jwt_secret.getBytes());
    }
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwt_expiration))
                .signWith(getSignInKey())
                .compact();
    }
    public String extractUsername(String jwt) {
        return Jwts.parser()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();
    }
    public boolean isTokenValid(String jwt, UserDetails user_details) {
        Claims claims = Jwts.parser()
                            .setSigningKey(getSignInKey())
                            .build()
                            .parseClaimsJws(jwt)
                            .getBody();
        String jwt_username = claims.getSubject();
        boolean expired = claims.getExpiration().before(new Date());
        return (jwt_username.equals(user_details.getUsername())) && !expired;
    }
}
