package com.habitat.service;

import com.habitat.dto.user.request.UserRegistrationRequestDto;
import com.habitat.dto.user.response.UserResponseDto;
import com.habitat.exception.RegistrationException;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto request) throws RegistrationException;
}