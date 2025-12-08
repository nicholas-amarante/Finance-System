package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name="Transaction")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="finance_transaction")
public class Transaction extends BaseModel{
    @NotBlank
    @Column(nullable = true)
    private String name;
    @Column(nullable = true)
    private BigDecimal value;
    @NotBlank
    @Column(nullable = true)
    private String description;

    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "cartao_credito_id")
    private CreditCard cartaoCredito;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public BigDecimal getValue() {return value;}
    public void setValue(BigDecimal value) {this.value = value;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
    public LocalDate getDate() {return date;}
    public void setDate(LocalDate date) {this.date = date;}
    public Account getAccount() {return account;}
    public void setAccount(Account account_id) {this.account=account_id;}
    public CreditCard getCartaoCredito() {return cartaoCredito;}
    public void setCartaoCredito(CreditCard cartaoCredito) {this.cartaoCredito = cartaoCredito;}
    public User getUser() {return user;}
    public void setUser(User user) {this.user = user;}
}
