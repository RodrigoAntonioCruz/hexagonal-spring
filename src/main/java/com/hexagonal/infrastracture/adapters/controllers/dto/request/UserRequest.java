package com.hexagonal.infrastracture.adapters.controllers.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hexagonal.infrastracture.adapters.controllers.dto.UserBaseDTO;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@JsonIgnoreProperties("id")
@EqualsAndHashCode(callSuper = true)
public class UserRequest extends UserBaseDTO {

}
