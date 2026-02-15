package com.example.demo.dto;

import java.math.BigDecimal;

public record DashboardDTO(
        BigDecimal totalIncome,
        BigDecimal totalExpense,
        BigDecimal balance
){}
