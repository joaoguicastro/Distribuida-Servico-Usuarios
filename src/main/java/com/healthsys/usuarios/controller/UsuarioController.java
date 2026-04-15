package com.healthsys.usuarios.controller;


import com.healthsys.usuarios.entity.UsuarioEntity;
import com.healthsys.usuarios.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastro")
    public ResponseEntity<UsuarioEntity> cadastrarUsuario(@RequestBody UsuarioEntity usuarioEntity) {
        UsuarioEntity usuarioCriado = usuarioService.salvarUsuario(usuarioEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCriado);
    }
}
