package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDTO {
    @NotBlank(message="O nome completo é obrigatório!")
    @Size(min=3, max=255)
    private String name;
    @NotBlank(message="O email é obrigatório!")
    @Email(message="O formato do email é inválido!")
    private String email;
    @NotBlank(message="O CPF é obrigatório!")
    @Size(min=11, max=11)
    private String cpf;
    @NotBlank(message="A senha é obrigatória!")
    @Size(min=8, message="A senha deve ter no mínimo 8 caracteres!")
    private String password;

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getCpf() {return cpf;}
    public void setCpf(String cpf) {this.cpf = cpf;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
}
