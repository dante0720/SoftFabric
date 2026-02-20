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
        return repository.save(funcionario);
    }

    public List<Funcionario> listar() {
        return repository.findAll();
    }

    public Optional<Funcionario> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Optional<Funcionario> actualizar(Long id, Funcionario datos) {
        return repository.findById(id).map(f -> {
            f.setNombre(datos.getNombre());
            f.setCargo(datos.getCargo());
            f.setSalario(datos.getSalario());
            f.setSindicato(datos.getSindicato());
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