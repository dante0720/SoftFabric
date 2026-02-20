package com.example.demo.dto;

import jakarta.validation.constraints.*;

public class FuncionarioDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 100)
    private String nombre;

    @NotBlank(message = "El cargo es obligatorio")
    @Size(min = 2, max = 50)
    private String cargo;

    @NotNull(message = "El salario es obligatorio")
    @Positive(message = "El salario debe ser mayor que cero")
    private Double salario;

    @NotBlank(message = "El sindicato es obligatorio")
    private String sindicato;

    public FuncionarioDTO() {}

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public Double getSalario() { return salario; }
    public void setSalario(Double salario) { this.salario = salario; }

    public String getSindicato() { return sindicato; }
    public void setSindicato(String sindicato) { this.sindicato = sindicato; }
}
