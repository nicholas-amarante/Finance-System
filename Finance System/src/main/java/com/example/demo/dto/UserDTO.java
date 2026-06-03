package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public interface UserDTO {

    record DisplayProfileData(
            String name,
            String email,
            String cpf,
            String birthday
    ){}

    record CreateUser(
            @NotBlank(message = "O nome é obrigatório!")
            @Size(min=3, max=255)
            String name,
            @NotBlank(message = "O email é obrigatório!")
            @Email(message = "O formato do email é obrigatório!")
            String email,
            @NotBlank(message = "O CPF é obrigatório!")
            @Size(min=11, max=11)
            String cpf,
            @NotBlank(message = "A data de nascimento é obrigatória!")
            @Size(min=10, max=10)
            String birthday,
            @NotBlank(message = "A senha é obrigatório!")
            @Size(min=8, message = "A senha deve ter no mínimo 8 caracteres!")
            String password
    ){}

    record UpdateUser(
            String name,
            String email,
            String cpf,
            String birthday
    ){}
}
