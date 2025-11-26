package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction extends BaseModel {

    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private BigDecimal value;

    private String description;

    @NotNull
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;

    // Relacionamento com Usuário (Muitas transações para 1 usuário)
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Relacionamento com Conta (De onde sai ou entra o dinheiro)
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    // Relacionamento com Cartão (Opcional, pois pode ser gasto em dinheiro)
    @ManyToOne
    @JoinColumn(name = "credit_card_id")
    private CreditCard creditCard;

    // --- CAMPO DO SEU AMIGO (DESTINATION) ---
    // Como a tabela ainda não existe, vou deixar como um campo simples (ID)
    // Quando seu amigo criar a tabela 'destination', você muda para @ManyToOne
    @Column(name = "destination_id")
    private Long destinationId;
}