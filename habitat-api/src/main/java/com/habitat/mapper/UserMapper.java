package com.habitat.mapper;

import com.habitat.config.MapperConfig;
import com.habitat.domain.User;
import com.habitat.dto.user.response.UserResponseDto;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserResponseDto toUserResponse(User user);
}
