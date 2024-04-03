package com.hexagonal.infrastructure.adapters.repositories.mappers;

import com.hexagonal.domain.User;
import com.hexagonal.infrastructure.adapters.repositories.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserOutputMapper {

    User toDomain(UserEntity userEntity);

    UserEntity toUserEntity(User user);

}

