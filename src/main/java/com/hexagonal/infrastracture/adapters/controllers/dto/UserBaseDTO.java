package com.hexagonal.infrastracture.adapters.controllers.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class UserBaseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -5237884468516326061L;

    @Schema(defaultValue = "Id do usuário", example = "64889fb6f3a692337867ea64")
    private String id;

    @Schema(defaultValue = "Nome do usuário", example = "Pedro da Costa")
    private String name;

    @Schema(defaultValue = "CPF do usuário", example = "85907544058")
    private String cpf;

    @Schema(defaultValue = "E-mail do usuário", example = "pedrocosta@gmail.com")
    private String email;
}
