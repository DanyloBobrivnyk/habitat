package com.habitat.web;

import com.habitat.dto.user.request.UserLoginRequestDto;
import com.habitat.dto.user.request.UserRegistrationRequestDto;
import com.habitat.dto.user.response.UserLoginResponseDto;
import com.habitat.dto.user.response.UserResponseDto;
import com.habitat.exception.RegistrationException;
import com.habitat.security.abstr.AuthenticationService;
import com.habitat.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication", description = "Endpoints for user registration and authentication")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserService userService;


    @PostMapping("/signup")
    @Operation(summary = "Register a new user")
    public ResponseEntity<UserResponseDto> signup(@Valid @RequestBody UserRegistrationRequestDto request) throws RegistrationException {
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/signin")
    @Operation(summary = "Authenticate a user")
    public ResponseEntity<UserLoginResponseDto> signin(@Valid @RequestBody UserLoginRequestDto request) {
        String token = authenticationService.signin(request);
        return ResponseEntity.ok(new UserLoginResponseDto(token));
    }
}