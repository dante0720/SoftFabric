package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import com.example.demo.model.Funcionario;
import com.example.demo.service.FuncionarioService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.example.demo.dto.FuncionarioDTO;

import java.util.List;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    private final FuncionarioService service;

    public FuncionarioController(FuncionarioService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<Funcionario> crear(
            @Valid @RequestBody FuncionarioDTO dto
    ) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNombre(dto.getNombre());
        funcionario.setCargo(dto.getCargo());
        funcionario.setSalario(dto.getSalario());
        funcionario.setSindicato(dto.getSindicato());

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

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody FuncionarioDTO dto
    ) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNombre(dto.getNombre());
        funcionario.setCargo(dto.getCargo());
        funcionario.setSalario(dto.getSalario());
        funcionario.setSindicato(dto.getSindicato());

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