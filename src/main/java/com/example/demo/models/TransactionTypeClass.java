package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;


@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="transaction_types")
public class TransactionTypeClass extends BaseModel{
    @Enumerated(EnumType.STRING)
    private TransactionType name;

    public TransactionType getName() {
        return name;
    }
    public void setName(TransactionType name) {this.name = name;}
}
