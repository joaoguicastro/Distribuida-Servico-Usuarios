package com.healthsys.usuarios.controller;

import com.healthsys.usuarios.dtos.AuthUsuarioRequestDTO;
import com.healthsys.usuarios.dtos.AuthUsuarioResponseDTO;
import com.healthsys.usuarios.entity.UsuarioEntity;
import com.healthsys.usuarios.repository.UsuarioRepository;
import com.healthsys.usuarios.util.JWTUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<AuthUsuarioResponseDTO> login(@Valid @RequestBody AuthUsuarioRequestDTO request) {
        UsuarioEntity user = repository.findByEmail(request.email())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario ou senha invalidos"));

        if (!passwordEncoder.matches(request.senha(), user.getSenha())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario ou senha invalidos");
        }

        String token = jwtUtil.generateToken(user);
        String expiraEm = Instant.now().plusMillis(jwtUtil.getExpirationInMillis()).toString();

        return ResponseEntity.ok(new AuthUsuarioResponseDTO(token, expiraEm));
    }
}
