package com.example.demo.dto;

import java.math.BigDecimal;

public record CreateTransactionDTO (
    String name,
    BigDecimal value,
    String description
)
{}
