package com.habitat.service.impl;

import com.habitat.domain.Role;
import com.habitat.domain.User;
import com.habitat.dto.user.request.UserRegistrationRequestDto;
import com.habitat.dto.user.response.UserResponseDto;
import com.habitat.exception.RegistrationException;
import com.habitat.mapper.UserMapper;
import com.habitat.repository.UserRepository;
import com.habitat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto request) throws RegistrationException {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RegistrationException("Unable to complete registration.");
        }

        User user = new User();
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setRole(Role.ADMIN);
        User savedUser = userRepository.save(user);
        return userMapper.toUserResponse(savedUser);

    }
}