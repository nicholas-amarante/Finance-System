package com.example.demo.controller;

import com.example.demo.dto.CreateTransactionDTO;
import com.example.demo.models.Transaction;
import com.example.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> create(@RequestBody CreateTransactionDTO dto) {
        Transaction newTransaction = transactionService.create(dto);
        return ResponseEntity.ok(newTransaction);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Transaction>> listByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(transactionService.listAllByUser(userId));
    }
}