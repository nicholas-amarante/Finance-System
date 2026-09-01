package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name="Transaction")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="finance_transaction")
public class Transaction extends BaseModel{
    @Setter
    @NotBlank
    @Column(nullable = true)
    private String name;

    @Setter
    @Column(nullable = true)
    private BigDecimal value;

    @Setter
    @Column(nullable = true)
    private String description;

    @Setter
    @Column(nullable = true)
    private LocalDateTime dateTime;

    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "creditCard_id")
    private CreditCard creditCard;

    @Setter
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @ManyToOne
    @JoinColumn(name = "transaction_type", nullable = false)
    private TransactionTypeClass transactionType;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name="category_transaction", nullable = false)
    private Category category;

    public String getName() {return name;}
    public BigDecimal getValue() {return value;}
    public String getDescription() {return description;}
    public LocalDateTime getDateTime() {return dateTime;}
    public Account getAccount() {return account;}
    public CreditCard getCreditCard() {return creditCard;}
    public void setCreditCard(CreditCard creditCard) {this.creditCard = creditCard;}
    public User getUser() {return user;}
}
