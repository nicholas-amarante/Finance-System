package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginUserDTO(
        @NotBlank(message = "O email não pode ser nulo!")
        String email,
        @NotBlank(message = "A senha não pode ser nulo!")
        String password
    )
{}
