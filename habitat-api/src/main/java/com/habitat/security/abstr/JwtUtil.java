package com.habitat.security.abstr;

public interface JwtUtil {
    String getUsernameFromToken(String token);

    String generateToken(String userName);

    boolean validateToken(String token);
}