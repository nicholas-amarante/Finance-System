package com.example.demo.dto;

import com.example.demo.models.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateTransactionDTO (
    @NotBlank(message = "O nome para a transação é obrigatória!")
    String name,
    @NotNull(message = "O valor não pode ser menor igual a zero!")
    BigDecimal value,
    String description,
    @NotNull(message = "Um usuário deve ser vinculado a transação!")
    Long account_id,
    @NotNull(message = "Um tipo de transação deve ser declarado!")
    TransactionType transactionType,
    @NotBlank(message = "Uma categoria deve ser definida para a transação!")
    String category
    )
{}
