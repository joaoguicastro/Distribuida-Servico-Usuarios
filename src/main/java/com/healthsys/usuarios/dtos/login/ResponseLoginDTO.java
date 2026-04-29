package com.healthsys.usuarios.dtos.login;

public record ResponseLoginDTO(
        String token_acesso,
        String perfil,
        String redirectUrl
) {
}
