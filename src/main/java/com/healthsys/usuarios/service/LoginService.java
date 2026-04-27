package com.healthsys.usuarios.service;

import com.healthsys.usuarios.dtos.login.LoginDTO;
import com.healthsys.usuarios.entity.UsuarioEntity;
import com.healthsys.usuarios.repository.UsuarioRepository;
import com.healthsys.usuarios.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class LoginService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTUtil jwtUtil;

    public String login(LoginDTO loginDTO) {
        UsuarioEntity usuario = usuarioRepository.findByEmail(loginDTO.email())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario ou senha invalidos"));

        if (!passwordEncoder.matches(loginDTO.password(), usuario.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario ou senha invalidos");
        }

        return jwtUtil.gerarToken(usuario);
    }
}
