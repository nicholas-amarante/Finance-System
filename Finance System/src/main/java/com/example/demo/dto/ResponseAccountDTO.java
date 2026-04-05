package com.example.demo.dto;

import com.example.demo.models.AccountType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ResponseAccountDTO(
        @NotNull(message = "O id da conta é obrigatório!")
        Long id,
        @NotBlank(message = "O nome da conta é obrigatório!")
        String bank_name,
        @NotBlank(message = "A descrição da conta é obrigatório!")
        String description,
        @NotNull(message = "O tipo da conta é obrigatório!")
        AccountType accountType
){}
