package com.hexagonal.infrastructure.adapters.controllers.mapper;

import com.hexagonal.domain.User;
import com.hexagonal.infrastructure.adapters.controllers.dto.request.UserRequest;
import com.hexagonal.infrastructure.adapters.controllers.dto.response.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserInputMapper {

    UserResponse toResponse(User user);

    User toEntity(UserRequest userRequest);

}


