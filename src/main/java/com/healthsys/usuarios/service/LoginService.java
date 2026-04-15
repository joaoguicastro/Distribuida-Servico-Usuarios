package com.healthsys.usuarios.service;


import com.healthsys.usuarios.dtos.login.LoginDTO;
import com.healthsys.usuarios.entity.UsuarioEntity;
import com.healthsys.usuarios.repository.UsuarioRepository;
import com.healthsys.usuarios.security.SecurityConfig;
import com.healthsys.usuarios.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private JWTUtil jwtUtil;

    public String login (LoginDTO loginDTO) {
        UsuarioEntity usuario = usuarioRepository.findByEmail(loginDTO.email()).orElseThrow(
                () -> new RuntimeException("Usuario NÃO encontrado!")
        );

        if (!securityConfig.passwordEncoder().matches(loginDTO.password(), usuario.getPassword())) {
            throw new RuntimeException("Senha inválida");
        }

        return jwtUtil.gerarToken(usuario);
    }
}
