package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DashboardDTO(
        @NotNull(message = "Total de receitas não pode ser nulo!")
        BigDecimal totalIncome,
        @NotNull(message = "Total de despesas não pode ser nulo!")
        BigDecimal totalExpense,
        @NotNull(message = "Saldo não pode ser nulo!")
        BigDecimal balance
){}
