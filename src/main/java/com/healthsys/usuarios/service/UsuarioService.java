package com.healthsys.usuarios.service;

import com.healthsys.usuarios.entity.UsuarioEntity;
import com.healthsys.usuarios.repository.UsuarioRepository;
import com.healthsys.usuarios.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SecurityConfig securityConfig;

    public UsuarioEntity cadastrarUsuario (UsuarioEntity usuario) {
        String senhaCodificada = securityConfig.passwordEncoder().encode(usuario.getSenha());
        UsuarioEntity usuarioCadastrado = usuarioRepository.save(usuario);

        return usuarioCadastrado.toBuilder()
                .senha(senhaCodificada)
                .build();
    }
}
