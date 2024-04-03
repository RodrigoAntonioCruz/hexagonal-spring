package com.hexagonal.infrastructure.adapters.controllers.mappers;

import com.hexagonal.domain.User;
import com.hexagonal.infrastructure.adapters.controllers.dtos.request.UserRequest;
import com.hexagonal.infrastructure.adapters.controllers.dtos.response.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserInputMapper {

    UserResponse toResponse(User user);

    User toEntity(UserRequest userRequest);

}


