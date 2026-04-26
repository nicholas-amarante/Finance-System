package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateUserDTO (
        @NotBlank(message = "O nome é obrigatório!")
        String name,
        @NotBlank(message = "O CPF é obrigatório!")
        String cpf,
        @NotBlank(message = "O email é obrigatório!")
        String email,
        @NotBlank(message = "A senha é obrigatória!")
        String password
    )
{}
