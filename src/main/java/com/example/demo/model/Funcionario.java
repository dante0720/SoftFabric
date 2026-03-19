package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String primerNombre;

    private String segundoNombre;

    @NotBlank
    private String primerApellido;

    private String segundoApellido;

    @Email
    @NotBlank
    private String correoInstitucional;

    @Email
    private String correoPersonal;

    @NotBlank
    private String numeroCelular;

    @NotBlank
    @Column(unique = true)
    private String documento;

    @NotBlank
    private String tipoDocumento;

    @JsonIgnore
    private String password;

    private Boolean isFuncionarioSecretaria = false;
}
