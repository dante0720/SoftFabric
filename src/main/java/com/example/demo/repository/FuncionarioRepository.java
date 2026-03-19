package com.example.demo.repository;

import com.example.demo.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    Optional<Funcionario> findByDocumento(String documento);

    Optional<Funcionario> findByCorreoInstitucional(String correoInstitucional);

}