package com.example.demo.auth;

import com.example.demo.model.Funcionario;
import com.example.demo.repository.FuncionarioRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final FuncionarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
            FuncionarioRepository repository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponse login(LoginRequest request) {

        Funcionario funcionario = repository
                .findByCorreoInstitucional(request.getCorreoInstitucional())
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));

        if (!passwordEncoder.matches(request.getPassword(), funcionario.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        if (!Boolean.TRUE.equals(funcionario.getIsFuncionarioSecretaria())) {
            throw new RuntimeException("Usuario no autorizado");
        }

        String token = jwtService.generateToken(funcionario);

        return new LoginResponse(token, "Bienvenido al sistema");
    }
}
