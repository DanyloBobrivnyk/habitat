package com.habitat.security.abstr;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtUtil {
    String extractUserName(String token);

    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);
}