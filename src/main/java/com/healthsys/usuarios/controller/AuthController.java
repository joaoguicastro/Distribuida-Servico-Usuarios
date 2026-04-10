package com.healthsys.usuarios.controller;

import com.healthsys.usuarios.dtos.AuthUsuarioRequestDTO;
import com.healthsys.usuarios.entity.UsuarioEntity;
import com.healthsys.usuarios.repository.UsuarioRepository;
import com.healthsys.usuarios.security.SecurityConfig;
import com.healthsys.usuarios.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestBody AuthUsuarioRequestDTO request) {

        UsuarioEntity user = repository.findByEmail(request.email())
                .orElseThrow();

        if (!securityConfig.passwordEncoder().matches(request.senha(), user.getSenha())) {
            throw new RuntimeException("Senha inválida");
        }

        return jwtUtil.generateToken(user);
    }
}
