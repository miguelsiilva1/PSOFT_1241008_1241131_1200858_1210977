package com.marslps.AISafeFMS.security;

import com.marslps.AISafeFMS.security.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwt_service;
    private final UserDetailsService user_details_service;

    public JwtAuthFilter(JwtService jwt_service, UserDetailsService user_details_service) {
        this.jwt_service = jwt_service;
        this.user_details_service = user_details_service;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorization_header = request.getHeader("Authorization");
        if (authorization_header == null || !authorization_header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        String jwt = authorization_header.substring(7);
        String username = jwt_service.extractUsername(jwt);
        UserDetails user_details = this.user_details_service.loadUserByUsername(username);
        if(jwt_service.isTokenValid(jwt, user_details)) {
            UsernamePasswordAuthenticationToken auth_token = new UsernamePasswordAuthenticationToken(user_details, null, user_details.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth_token);
        }
        filterChain.doFilter(request, response);
    }
}
