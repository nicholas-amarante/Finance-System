package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateBankDTO(
        @NotBlank(message = "O nome do banco é obrigatório!")
        String name
) {}
