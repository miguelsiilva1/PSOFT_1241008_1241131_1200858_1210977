package com.marslps.AISafeFMS.security.service;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String jwt_secret;
    @Value("${jwt.expiration}")
    private Long jwt_expiration;

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwt_expiration))
                .signWith(SignatureAlgorithm.HS256, jwt_secret)
                .compact();
    }
    public String extractUsername(String jwt) {

    }
    public boolean isTokenValid(String jwt, UserDetails user_details) {

    }
}
