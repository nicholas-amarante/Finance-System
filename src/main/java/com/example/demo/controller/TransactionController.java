package com.example.demo.controller;

import com.example.demo.dto.PagedDTO;
import com.example.demo.dto.TransactionDTO;
import com.example.demo.models.User;
import com.example.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {this.transactionService = transactionService;}

    @PostMapping()
    public ResponseEntity<Void> createTransaction(@RequestBody TransactionDTO.CreateTransactionDTO createTransactionDTO){
        transactionService.createTransaction(createTransactionDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PagedDTO.PagedResponse<TransactionDTO.TransactionFeed>> getTransaction(
            @AuthenticationPrincipal User loggedUser,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Long accountId,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        var response=transactionService.getFilteredTransaction(loggedUser, description, type, category, accountId, startDate, endDate, page, size);
        return ResponseEntity.ok(response);
    }
}
