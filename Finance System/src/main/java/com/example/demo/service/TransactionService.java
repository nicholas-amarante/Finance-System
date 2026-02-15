package com.example.demo.service;

import com.example.demo.dto.CreateTransactionDTO;
import com.example.demo.models.*;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.TransactionTypeRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionTypeRepository transactionTypeRepository;
    @Autowired
    private AuthenticationService authenticationService;

    @Transactional
    public void createTransaction(@RequestBody CreateTransactionDTO createTransactionDTO){
        User currentUser=authenticationService.getLoggedUser();
        Transaction transaction=new Transaction();
        Account account=accountRepository.findById(createTransactionDTO.account_id())
                        .orElseThrow(()->new RuntimeException("Account unable to match ID: "+createTransactionDTO.account_id()));
        transaction.setName(createTransactionDTO.name());
        transaction.setDescription(createTransactionDTO.description());
        transaction.setValue(createTransactionDTO.value());
        if (createTransactionDTO.transactionType() == null) {
            throw new IllegalArgumentException("O tipo de transação não pode ser nulo.");
        }
        TransactionTypeClass transactionTypeClass=transactionTypeRepository.findByName(createTransactionDTO.transactionType())
                .orElseThrow(()->new RuntimeException("Erro critico! "+createTransactionDTO.transactionType()+" Type nao encontrado"));
        transaction.setDateTime(LocalDateTime.now());
        transaction.setUser(currentUser);
        transaction.setAccount(account);
        transaction.setTransactionType(transactionTypeClass);
        transactionRepository.save(transaction);
    }

}
