package com.healthsys.usuarios.dtos.usuario;

public record UsuarioDTO (
        Long id,
        String name,
        String email,
        String password
){
}
