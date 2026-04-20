package com.healthsys.usuarios.controller;

import com.healthsys.usuarios.dtos.login.LoginDTO;
import com.healthsys.usuarios.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private LoginService loginService;

    @PostMapping({"", "/login"})
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody LoginDTO loginDTO) {
        String token = loginService.login(loginDTO);
        return ResponseEntity.ok(Map.of("token_acesso", token));
    }
}
