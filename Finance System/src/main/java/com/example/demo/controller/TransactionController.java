package com.example.demo.controller;

import com.example.demo.dto.CreateTransactionDTO;
import com.example.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {this.transactionService = transactionService;}

    @PostMapping()
    public ResponseEntity<Void> createTransaction(@RequestBody CreateTransactionDTO createTransactionDTO){
        transactionService.createTransaction(createTransactionDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
