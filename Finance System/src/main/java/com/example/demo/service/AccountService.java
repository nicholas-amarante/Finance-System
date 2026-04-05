package com.example.demo.service;

import com.example.demo.dto.CreateAccountDTO;
import com.example.demo.models.Account;
import com.example.demo.models.Bank;
import com.example.demo.models.User;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.BankRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private BankRepository bankRepository;

    @Transactional
    public void createAccount(@RequestBody CreateAccountDTO createAccountDTO) {
        User currentUser=authenticationService.getLoggedUser();
        Bank bank=bankRepository.findById(createAccountDTO.bank_id())
                .orElseThrow(()->new RuntimeException("Bank unable to match ID: "+ createAccountDTO.bank_id()));
        Account account = new Account();
        account.setUser(currentUser);
        account.setBank(bank);
        account.setDescription(createAccountDTO.description());
        account.setCurrentBalance(createAccountDTO.currentBalance());
        account.setAccountType(createAccountDTO.accountType());
        accountRepository.save(account);
    }

    public List<Account> findByUser(){
        User currentUser=authenticationService.getLoggedUser();
        return accountRepository.findByUser(currentUser);
    }
    
}
