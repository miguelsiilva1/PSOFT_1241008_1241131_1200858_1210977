package com.marslps.AISafeFMS.security.model.vo;

public record TokenResponse(String token) {
    public TokenResponse(String token) {
        this.token = token;
    }
}
