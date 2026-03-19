package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import com.example.demo.model.Funcionario;
import com.example.demo.service.FuncionarioService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.example.demo.dto.FuncionarioDTO;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    private final FuncionarioService service;
    private final PasswordEncoder passwordEncoder;

    public FuncionarioController(FuncionarioService service, PasswordEncoder passwordEncoder) {
        this.service = service;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<Funcionario> crear(@Valid @RequestBody FuncionarioDTO dto) {

        Funcionario funcionario = new Funcionario();

        funcionario.setPrimerNombre(dto.getPrimerNombre());
        funcionario.setSegundoNombre(dto.getSegundoNombre());
        funcionario.setPrimerApellido(dto.getPrimerApellido());
        funcionario.setSegundoApellido(dto.getSegundoApellido());
        funcionario.setCorreoInstitucional(dto.getCorreoInstitucional());
        funcionario.setCorreoPersonal(dto.getCorreoPersonal());
        funcionario.setNumeroCelular(dto.getNumeroCelular());
        funcionario.setDocumento(dto.getDocumento());
        funcionario.setTipoDocumento(dto.getTipoDocumento());

        // 🔐 Encriptar contraseña
        funcionario.setPassword(passwordEncoder.encode(dto.getPassword()));

        funcionario.setIsFuncionarioSecretaria(dto.getIsFuncionarioSecretaria());

        Funcionario guardado = service.guardar(funcionario);

        return ResponseEntity.status(201).body(guardado);
    }

    @GetMapping
    public List<Funcionario> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> obtenerPorId(@PathVariable Long id) {

        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/documento/{documento}")
    public ResponseEntity<Funcionario> obtenerPorDocumento(@PathVariable String documento) {

        return service.buscarPorDocumento(documento)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody FuncionarioDTO dto
    ) {

        Funcionario funcionario = new Funcionario();

        funcionario.setPrimerNombre(dto.getPrimerNombre());
        funcionario.setSegundoNombre(dto.getSegundoNombre());
        funcionario.setPrimerApellido(dto.getPrimerApellido());
        funcionario.setSegundoApellido(dto.getSegundoApellido());
        funcionario.setCorreoInstitucional(dto.getCorreoInstitucional());
        funcionario.setCorreoPersonal(dto.getCorreoPersonal());
        funcionario.setNumeroCelular(dto.getNumeroCelular());
        funcionario.setDocumento(dto.getDocumento());
        funcionario.setTipoDocumento(dto.getTipoDocumento());

        return service.actualizar(id, funcionario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        if (!service.existePorId(id)) {
            throw new RuntimeException("Funcionario no encontrado");
        }

        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}