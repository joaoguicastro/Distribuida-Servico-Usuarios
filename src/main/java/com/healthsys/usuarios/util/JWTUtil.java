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

    @Value("{security.token.secret}")
    private String secretKey;

    public String generateToken (UsuarioEntity usuario) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        Instant expiresIn = Instant.now().plus(Duration.ofMinutes(59));

        String token = JWT.create()
                .withIssuer("healthSys")
                .withSubject(usuario.getNome())
                .withClaim("role", usuario.getPerfil().name())
                .withExpiresAt(expiresIn)
                .sign(algorithm);

        return token;
    }

    public DecodedJWT validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        return JWT.require(algorithm)
                .withIssuer("healthSys")
                .build()
                .verify(token);
    }
}
