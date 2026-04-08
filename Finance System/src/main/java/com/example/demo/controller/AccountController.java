package com.example.demo.controller;

import com.example.demo.dto.CreateAccountDTO;
import com.example.demo.dto.ResponseAccountDTO;
import com.example.demo.models.Account;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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

    @GetMapping("/my-accounts")
    public ResponseEntity<List<ResponseAccountDTO>> getMyAccounts(){
        List<Account> myAccounts=accountService.findByUser();
        List<ResponseAccountDTO> dtos=myAccounts.stream()
                .map(account -> new ResponseAccountDTO(
                        account.getId(),
                        account.getBank().getName(),
                        account.getDescription(),
                        account.getAccountType()
                ))
                .toList();
        return ResponseEntity.ok(dtos);
    }
}
