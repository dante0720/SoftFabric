package com.example.demo.controller;

import com.example.demo.model.Funcionario;
import com.example.demo.service.FuncionarioService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class FuncionarioGraphQLController {

    private final FuncionarioService service;

    public FuncionarioGraphQLController(FuncionarioService service) {
        this.service = service;
    }

    // Query: listar todos
    @QueryMapping
    public List<Funcionario> funcionarios() {
        return service.listar();
    }

    // Query: buscar por ID
    @QueryMapping
    public Funcionario funcionarioPorId(@Argument Long id) {
        return service.buscarPorId(id).orElse(null);
    }
}