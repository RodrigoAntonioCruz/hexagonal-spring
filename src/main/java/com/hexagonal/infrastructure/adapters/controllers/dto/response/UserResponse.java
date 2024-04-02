package com.hexagonal.infrastructure.adapters.controllers.dto.response;

import com.hexagonal.infrastructure.adapters.controllers.dto.UserBaseDTO;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserResponse extends UserBaseDTO {

}