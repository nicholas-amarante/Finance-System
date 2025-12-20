package com.example.demo.dto;

import com.example.demo.models.Account;

import java.math.BigDecimal;

public record CreateTransactionDTO (
    String name,
    BigDecimal value,
    String description,
    Long account_id
    )
{}
