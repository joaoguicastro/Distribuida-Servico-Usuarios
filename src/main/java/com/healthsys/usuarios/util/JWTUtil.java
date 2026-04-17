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

    @Value("${security.token.secret}")
    private String secretKey;

    @Value("${jwt.expiration:86400000}")
    private long expirationInMillis;

    public String generateToken(UsuarioEntity usuario) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        Instant expiresIn = Instant.now().plus(Duration.ofMillis(expirationInMillis));

        return JWT.create()
                .withIssuer("healthSys")
                .withSubject(usuario.getEmail())
                .withClaim("nome", usuario.getNome())
                .withClaim("role", usuario.getPerfil().name())
                .withExpiresAt(expiresIn)
                .sign(algorithm);
    }

    public DecodedJWT validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        return JWT.require(algorithm)
                .withIssuer("healthSys")
                .build()
                .verify(token);
    }

    public long getExpirationInMillis() {
        return expirationInMillis;
    }
}
