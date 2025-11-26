package com.example.demo.repository;

import com.example.demo.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // Buscar todas as transações de um usuário específico
    List<Transaction> findByUserId(Long userId);
}