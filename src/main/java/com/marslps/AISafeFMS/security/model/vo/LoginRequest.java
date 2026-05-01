package com.marslps.AISafeFMS.security.model.vo;

public record LoginRequest(String username, String password) {
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
