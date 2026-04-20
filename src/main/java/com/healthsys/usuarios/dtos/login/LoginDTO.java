package com.healthsys.usuarios.dtos.login;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @NotBlank(message = "Email e obrigatorio")
        @Email(message = "Email invalido")
        String email,
        @JsonAlias("senha")
        @NotBlank(message = "Senha e obrigatoria")
        String password
) {
}
