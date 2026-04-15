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

    public UsuarioEntity salvarUsuario (UsuarioEntity usuario) {
        String senha = usuario.getPassword();

        String senhaCriptografada = securityConfig.passwordEncoder().encode(senha);

        return usuarioRepository.save(usuario.toBuilder()
                .password(senhaCriptografada)
                .build());
    }
}
