package com.habitat.security;

import com.habitat.dto.user.request.UserLoginRequestDto;
import com.habitat.security.abstr.AuthenticationService;
import com.habitat.security.abstr.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public String signin(UserLoginRequestDto request) {
        final Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password()));

        return jwtUtil.generateToken(auth.getName());
    }
}