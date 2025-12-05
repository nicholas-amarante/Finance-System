package com.example.demo.service;


import com.example.demo.dto.CreateTransactionDTO;
import com.example.demo.models.Transaction;
import com.example.demo.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    @Transactional
    public void createTransaction(CreateTransactionDTO createTransactionDTO) {
        Transaction newTransaction=Transaction.builder()
                .name(createTransactionDTO.name())
                .value(createTransactionDTO.value())
                .description(createTransactionDTO.description())
                .build();
        transactionRepository.save(newTransaction);
    }
}
