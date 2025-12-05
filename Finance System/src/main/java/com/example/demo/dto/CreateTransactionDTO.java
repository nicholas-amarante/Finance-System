package com.example.demo.dto;

public record CreateTransactionDTO (
    String name,
    float value,
    String description
)
{}
