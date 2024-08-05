package com.habitat.security;

import com.habitat.dto.request.SignInRequest;
import com.habitat.dto.request.SignUpRequest;
import com.habitat.dto.response.JwtAuthenticationResponse;
import com.habitat.domain.Role;
import com.habitat.domain.User;
import com.habitat.repository.UserRepository;
import com.habitat.security.abstr.AuthenticationService;
import com.habitat.security.abstr.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(Role.PAID_USER).build();
        userRepository.save(user);
        //TODO: work-out unique email violation
        var jwt = jwtUtil.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtUtil.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}