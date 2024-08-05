package com.habitat.security.abstr;

import com.habitat.dto.request.SignInRequest;
import com.habitat.dto.request.SignUpRequest;
import com.habitat.dto.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SignInRequest request);
}
