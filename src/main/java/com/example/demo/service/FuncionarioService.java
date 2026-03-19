package com.example.demo.service;

import com.example.demo.model.Funcionario;
import com.example.demo.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    private final FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    public Funcionario guardar(Funcionario funcionario) {

        Optional<Funcionario> existente = repository.findByDocumento(funcionario.getDocumento());

        if (existente.isPresent()) {
            throw new RuntimeException("Ya existe un funcionario con el documento: " + funcionario.getDocumento());
        }

        if (repository.findByCorreoInstitucional(funcionario.getCorreoInstitucional()).isPresent()) {
            throw new RuntimeException("El correo institucional ya está registrado");
        }

        return repository.save(funcionario);
    }

    public List<Funcionario> listar() {
        return repository.findAll();
    }

    public Optional<Funcionario> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Optional<Funcionario> buscarPorDocumento(String documento) {
        return repository.findByDocumento(documento);
    }

    public Optional<Funcionario> actualizar(Long id, Funcionario datos) {
        return repository.findById(id).map(f -> {

            f.setPrimerNombre(datos.getPrimerNombre());
            f.setSegundoNombre(datos.getSegundoNombre());
            f.setPrimerApellido(datos.getPrimerApellido());
            f.setSegundoApellido(datos.getSegundoApellido());
            f.setCorreoInstitucional(datos.getCorreoInstitucional());
            f.setCorreoPersonal(datos.getCorreoPersonal());
            f.setNumeroCelular(datos.getNumeroCelular());
            f.setDocumento(datos.getDocumento());
            f.setTipoDocumento(datos.getTipoDocumento());

            return repository.save(f);
        });
    }

    public boolean existePorId(Long id) {
        return repository.existsById(id);
    }

    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Funcionario no encontrado con id " + id);
        }
        repository.deleteById(id);
    }

}