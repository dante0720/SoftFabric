package com.example.demo.auth;

import com.example.demo.model.Funcionario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    // 🔐 clave secreta para firmar el token
    private static final String SECRET_KEY =
            "mi_clave_secreta_super_segura_para_jwt_2026_demo_proyecto";

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // extraer correo del token
    public String extractCorreo(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // extraer cualquier dato del token
    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        final Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    // obtener todos los claims
    private Claims extractAllClaims(String token) {

        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // generar token
    public String generateToken(Funcionario funcionario) {

        return Jwts.builder()

                .setSubject(funcionario.getCorreoInstitucional())

                .claim("id", funcionario.getId())

                .setIssuedAt(new Date(System.currentTimeMillis()))

                .setExpiration(
                        new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)
                )

                .signWith(getSignKey(), SignatureAlgorithm.HS256)

                .compact();
    }

    // validar token
    public boolean isTokenValid(String token, Funcionario funcionario) {

        final String correo = extractCorreo(token);

        return correo.equals(funcionario.getCorreoInstitucional())
                && !isTokenExpired(token);
    }

    // verificar expiración
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // obtener fecha de expiración
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}