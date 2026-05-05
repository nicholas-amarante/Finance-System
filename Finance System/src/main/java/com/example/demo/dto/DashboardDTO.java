package com.example.demo.dto;

import com.example.demo.models.TransactionType;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public interface DashboardDTO{

        record CalculateIncomeAndExpenseResponseByMonth(
                @NotNull(message = "Total de receitas não pode ser nulo!")
                BigDecimal totalIncome,
                @NotNull(message = "Total de despesas não pode ser nulo!")
                BigDecimal totalExpense,
                @NotNull(message = "Saldo não pode ser nulo!")
                BigDecimal balance
        ){}

        record CalculateIncomeAndExpenseResponseByCategorys(
                String categoryName,
                BigDecimal totalAmount
        ){}

        record CalculateIncomeAndExpenseByPeriods(
                Integer year,
                Integer month,
                TransactionType transactionType,
                BigDecimal totalAmount
        ){}
}
