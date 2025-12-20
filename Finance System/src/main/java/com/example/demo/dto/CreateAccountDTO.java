package com.example.demo.dto;

import com.example.demo.models.AccountType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CreateAccountDTO(
        @NotNull(message = "A descrição da conta é obrigatório")
        String description,
        @NotNull(message = "O saldo inicial deve ser obrigatório")
        @Positive(message = "O saldo inicial não pode ser negativo")
        BigDecimal currentBalance,
        @NotNull(message = "O tipo de conta deve ser preenchido")
        AccountType accountType,
        @NotNull(message = "O banco deve ser preenchido")
        Long bank_id
){}
