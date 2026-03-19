package com.example.demo.controller;

import com.example.demo.model.Funcionario;
import com.example.demo.service.FuncionarioService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class FuncionarioGraphQLController {

    private final FuncionarioService service;

    public FuncionarioGraphQLController(FuncionarioService service) {
        this.service = service;
    }

    // QUERY — listar todos
    @QueryMapping
    public List<Funcionario> funcionarios() {
        return service.listar();
    }

    // QUERY — buscar por ID
    @QueryMapping
    public Funcionario funcionarioPorId(@Argument Long id) {
        return service.buscarPorId(id).orElse(null);
    }

    // QUERY — buscar por documento
    @QueryMapping
    public Funcionario funcionarioPorDocumento(@Argument String documento) {
        return service.buscarPorDocumento(documento).orElse(null);
    }

    // MUTATION — crear funcionario
    @MutationMapping
    public Funcionario crearFuncionario(
            @Argument String primerNombre,
            @Argument String segundoNombre,
            @Argument String primerApellido,
            @Argument String segundoApellido,
            @Argument String correoInstitucional,
            @Argument String correoPersonal,
            @Argument String numeroCelular,
            @Argument String documento,
            @Argument String tipoDocumento
    ) {

        Funcionario funcionario = new Funcionario();

        funcionario.setPrimerNombre(primerNombre);
        funcionario.setSegundoNombre(segundoNombre);
        funcionario.setPrimerApellido(primerApellido);
        funcionario.setSegundoApellido(segundoApellido);
        funcionario.setCorreoInstitucional(correoInstitucional);
        funcionario.setCorreoPersonal(correoPersonal);
        funcionario.setNumeroCelular(numeroCelular);
        funcionario.setDocumento(documento);
        funcionario.setTipoDocumento(tipoDocumento);

        return service.guardar(funcionario);
    }

    // MUTATION — actualizar funcionario
    @MutationMapping
    public Funcionario actualizarFuncionario(
            @Argument Long id,
            @Argument String primerNombre,
            @Argument String segundoNombre,
            @Argument String primerApellido,
            @Argument String segundoApellido,
            @Argument String correoInstitucional,
            @Argument String correoPersonal,
            @Argument String numeroCelular,
            @Argument String documento,
            @Argument String tipoDocumento
    ) {

        Funcionario funcionario = new Funcionario();

        funcionario.setPrimerNombre(primerNombre);
        funcionario.setSegundoNombre(segundoNombre);
        funcionario.setPrimerApellido(primerApellido);
        funcionario.setSegundoApellido(segundoApellido);
        funcionario.setCorreoInstitucional(correoInstitucional);
        funcionario.setCorreoPersonal(correoPersonal);
        funcionario.setNumeroCelular(numeroCelular);
        funcionario.setDocumento(documento);
        funcionario.setTipoDocumento(tipoDocumento);

        return service.actualizar(id, funcionario).orElse(null);
    }

    // MUTATION — eliminar funcionario
    @MutationMapping
    public Boolean eliminarFuncionario(@Argument Long id) {

        if (!service.existePorId(id)) {
            return false;
        }

        service.eliminar(id);
        return true;
    }
}