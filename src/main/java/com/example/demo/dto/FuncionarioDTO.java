package com.example.demo.dto;

import jakarta.validation.constraints.*;

public class FuncionarioDTO {

    @NotBlank(message = "El primer nombre es obligatorio")
    @Size(min = 2, max = 50)
    private String primerNombre;

    private String segundoNombre;

    @NotBlank(message = "El primer apellido es obligatorio")
    @Size(min = 2, max = 50)
    private String primerApellido;

    private String segundoApellido;

    @Email(message = "Correo institucional inválido")
    @NotBlank(message = "El correo institucional es obligatorio")
    private String correoInstitucional;

    @Email(message = "Correo personal inválido")
    private String correoPersonal;

    @NotBlank(message = "El número celular es obligatorio")
    private String numeroCelular;

    @NotBlank(message = "El documento es obligatorio")
    private String documento;

    @NotBlank(message = "El tipo de documento es obligatorio")
    private String tipoDocumento;


    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    private Boolean isFuncionarioSecretaria;

    public FuncionarioDTO() {}


    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getCorreoInstitucional() {
        return correoInstitucional;
    }

    public void setCorreoInstitucional(String correoInstitucional) {
        this.correoInstitucional = correoInstitucional;
    }

    public String getCorreoPersonal() {
        return correoPersonal;
    }

    public void setCorreoPersonal(String correoPersonal) {
        this.correoPersonal = correoPersonal;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsFuncionarioSecretaria() {
        return isFuncionarioSecretaria;
    }

    public void setIsFuncionarioSecretaria(Boolean isFuncionarioSecretaria) {
        this.isFuncionarioSecretaria = isFuncionarioSecretaria;
    }
}
