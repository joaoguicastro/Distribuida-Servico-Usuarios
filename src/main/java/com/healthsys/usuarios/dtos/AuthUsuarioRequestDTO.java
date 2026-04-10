package com.healthsys.usuarios.dtos;

public record AuthUsuarioRequestDTO (
        String email,
        String senha
) {
}
