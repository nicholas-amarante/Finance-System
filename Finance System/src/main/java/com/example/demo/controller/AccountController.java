package com.example.demo.controller;

import com.example.demo.dto.CreateAccountDTO;
import com.example.demo.models.Account;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    public AccountController(AccountService accountService) {this.accountService = accountService;}

    @PostMapping()
    public ResponseEntity<Void> createAccount(@RequestBody CreateAccountDTO createAccountDTO) {
        accountService.createAccount(createAccountDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
