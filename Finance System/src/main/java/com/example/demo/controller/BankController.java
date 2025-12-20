package com.example.demo.controller;

import com.example.demo.dto.CreateBankDTO;
import com.example.demo.models.Bank;
import com.example.demo.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/banks")
public class BankController {
    @Autowired
    private BankService bankService;

    @PostMapping()
    public ResponseEntity<Bank> createBank(@RequestBody CreateBankDTO createBankDTO) {
        bankService.createBank(createBankDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
