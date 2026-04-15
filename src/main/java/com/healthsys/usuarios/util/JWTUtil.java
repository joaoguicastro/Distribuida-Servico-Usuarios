package com.healthsys.usuarios.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.healthsys.usuarios.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Component
public class JWTUtil {

    @Value("{jwt.secret}")
    private String secretKey;

    public String gerarToken (UsuarioEntity usuario) {

        String email = usuario.getEmail();

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        Instant expiresIn = Instant.now().plus(Duration.ofHours(2));

        String tokenGerado = JWT.create()
                .withSubject(email)
                .withClaim("role", usuario.getRole().name())
                .withExpiresAt(expiresIn)
                .sign(algorithm);

        return tokenGerado;
    }

    public DecodedJWT validarToken (String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        return JWT.require(algorithm)
                .build()
                .verify(token);
    }
}
