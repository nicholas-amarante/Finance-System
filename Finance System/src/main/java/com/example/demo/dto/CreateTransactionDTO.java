package com.example.demo.dto;

import com.example.demo.models.TransactionType;
import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateTransactionDTO(
        String name,
        BigDecimal value,
        String description,
        LocalDate date,
        TransactionType type,
        Long userId,
        Long accountId,       // ID da conta usada (se houver)
        Long creditCardId,    // ID do cartão usado (se houver)
        Long destinationId    // ID do destino (se houver)
) {}