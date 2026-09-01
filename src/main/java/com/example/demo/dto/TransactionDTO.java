package com.example.demo.dto;

import com.example.demo.models.Transaction;
import com.example.demo.models.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public interface TransactionDTO{

    record CreateTransactionDTO(
            @NotBlank(message = "O nome para a transação é obrigatória!")
            String name,
            String description,
            @NotNull(message = "O valor não pode ser menor igual a zero!")
            BigDecimal value,
            @NotNull(message = "Data não pode ser nula")
            LocalDateTime dateTime,
            @NotNull(message = "Um tipo de transação deve ser declarado!")
            TransactionType transactionType,
            @NotBlank(message = "Uma categoria deve ser definida para a transação!")
            String category,
            @NotNull(message = "Um usuário deve ser vinculado a transação!")
            Long account_id
    ){}

    record ResponseTransactionDTO(
            Long id,
            String name,
            String description,
            BigDecimal value,
            LocalDateTime dateTime,
            String transactionType,
            String categoryName,
            Long account_id
    ){
        public static ResponseTransactionDTO fromEntity(Transaction transaction){
            return new TransactionDTO.ResponseTransactionDTO(
                    transaction.getId(),
                    transaction.getName(),
                    transaction.getDescription(),
                    transaction.getValue(),
                    transaction.getDateTime(),
                    transaction.getTransactionType().getName().name(),
                    transaction.getCategory().getName(),
                    transaction.getAccount().getId()
            );
        }
    }

    record TransactionFeed(
            Long id,
            String name,
            String description,
            BigDecimal value,
            String category,
            TransactionType transactionType,
            LocalDateTime dateTime,
            String bank
    ){}
}
