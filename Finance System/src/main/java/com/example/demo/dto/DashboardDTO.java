package com.example.demo.dto;

import com.example.demo.models.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface DashboardDTO{

        record CalculateIncomeAndExpenseByMonthResponse(
                BigDecimal totalIncome,
                BigDecimal totalExpense,
                BigDecimal balance
        ){}

        record CalculateIncomeAndExpenseByCategorysResponse(
                String categoryName,
                BigDecimal totalAmount
        ){}

        record CalculateIncomeAndExpenseByPeriodsResponse(
                Long id,
                String name,
                String description,
                BigDecimal value,
                String categoryName,
                TransactionType transactionType,
                LocalDateTime dateTime
        ){}
}
