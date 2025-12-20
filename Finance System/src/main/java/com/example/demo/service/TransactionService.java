package com.example.demo.service;

import com.example.demo.dto.CreateTransactionDTO;
import com.example.demo.models.Account;
import com.example.demo.models.Transaction;
import com.example.demo.models.User;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AuthenticationService authenticationService;

//    @Transactional
//    public void createTransaction(CreateTransactionDTO createTransactionDTO) {
//        Transaction newTransaction=Transaction.builder()
//                .name(createTransactionDTO.name())
//                .value(createTransactionDTO.value())
//                .description(createTransactionDTO.description())
//                .build();
//        transactionRepository.save(newTransaction);
//    }

    @Transactional
    public void createTransaction(@RequestBody CreateTransactionDTO createTransactionDTO){
        User currentUser=authenticationService.getLoggedUser();
        Transaction transaction=new Transaction();
        Account account=accountRepository.findById(createTransactionDTO.account_id())
                        .orElseThrow(()->new RuntimeException("Account unable to match ID: "+createTransactionDTO.account_id()));
        transaction.setName(createTransactionDTO.name());
        transaction.setDescription(createTransactionDTO.description());
        transaction.setValue(createTransactionDTO.value());
        transaction.setDate(LocalDate.now());
        transaction.setUser(currentUser);
        transaction.setAccount(account);
        transaction.setTime(LocalTime.now());
        transactionRepository.save(transaction);
    }

}
