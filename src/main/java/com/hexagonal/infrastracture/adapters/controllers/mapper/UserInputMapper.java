package com.hexagonal.infrastracture.adapters.controllers.mapper;

import com.hexagonal.domain.User;
import com.hexagonal.infrastracture.adapters.controllers.dto.request.UserRequest;
import com.hexagonal.infrastracture.adapters.controllers.dto.response.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserInputMapper {

    UserResponse toResponse(User user);

    User toEntity(UserRequest userRequest);

}


