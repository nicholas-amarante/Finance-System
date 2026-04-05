package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Account extends BaseModel{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    @NotNull
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id", nullable = false)
    @NotNull
    private Bank bank;
    @NotNull
    private String description;
    @NotNull
    @Column(nullable = false)
    private BigDecimal currentBalance;
    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(nullable = false)
    private AccountType accountType;

    public User getUser() {return user;}
    public void setUser(User user) {this.user = user;}
    public Bank getBank() {return bank;}
    public void setBank(Bank bank) {this.bank = bank;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
    public BigDecimal getCurrentBalance() {return currentBalance;}
    public void setCurrentBalance(BigDecimal currentBalance) {this.currentBalance = currentBalance;}
    public AccountType getAccountType() {return accountType;}
    public void setAccountType(AccountType accountType) {this.accountType = accountType;}
}
