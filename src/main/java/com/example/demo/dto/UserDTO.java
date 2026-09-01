package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public interface UserDTO {

    record DisplayProfileData(
            String name,
            String email,
            String cpf,
            LocalDate birthday
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
            @NotNull(message = "A data de nascimento é obrigatória!")
            @Past(message = "A data de nascimento deve ser uma data no passado!")
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
            LocalDate birthday,
            @NotBlank(message = "A senha é obrigatório!")
            @Size(min=8, message = "A senha deve ter no mínimo 8 caracteres!")
            String password
    ){}

    record UpdateUser(
            String name,
            String email,
            String cpf,
            LocalDate birthday
    ){}
}
