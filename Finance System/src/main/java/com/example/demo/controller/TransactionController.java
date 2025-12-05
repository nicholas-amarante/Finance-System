package com.example.demo.controller;

import com.example.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {this.transactionService = transactionService;}


}
