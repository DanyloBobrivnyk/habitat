package com.habitat.security.abstr;

import com.habitat.dto.user.request.UserLoginRequestDto;

public interface AuthenticationService {
    String signin(UserLoginRequestDto request);
}
