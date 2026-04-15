package com.healthsys.usuarios.controller;


import com.healthsys.usuarios.dtos.login.LoginDTO;
import com.healthsys.usuarios.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public String login (@RequestBody LoginDTO loginDTO) {
        return loginService.login(loginDTO);
    }
}
