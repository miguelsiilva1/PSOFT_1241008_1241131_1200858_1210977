package com.marslps.AISafeFMS.security.controller;

import com.marslps.AISafeFMS.security.model.vo.LoginRequest;
import com.marslps.AISafeFMS.security.model.vo.TokenResponse;
import com.marslps.AISafeFMS.security.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class AuthController {
    private final AuthenticationManager authentication_manager;
    private final JwtService jwt_service;

    public AuthController(AuthenticationManager authentication_manager, JwtService jwt_service) {
        this.authentication_manager = authentication_manager;
        this.jwt_service = jwt_service;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest request) {
        authentication_manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );
        String jwtToken = jwt_service.generateToken(request.username());
        return ResponseEntity.ok(new TokenResponse(jwtToken));
    }
}
