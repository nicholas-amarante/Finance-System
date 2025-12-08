package com.example.demo.service;


import com.example.demo.dto.CreateTransactionDTO;
import com.example.demo.models.Transaction;
import com.example.demo.models.User;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    private UserRepository userRepository;

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
        Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userEmail;
        if(principal instanceof UserDetails){
            userEmail=((UserDetails) principal).getUsername();
        }else{
            userEmail=principal.toString();
        }
        System.out.println("UserEmail: "+userEmail);
        User currentUser=userRepository.findByEmail(userEmail)
                .orElseThrow(()->new RuntimeException("User not found on DB"));
        Transaction transaction=new Transaction();
        transaction.setName(createTransactionDTO.name());
        transaction.setDescription(createTransactionDTO.description());
        transaction.setValue(createTransactionDTO.value());
        transaction.setDate(LocalDate.now());
        transaction.setUser(currentUser);
        transactionRepository.save(transaction);
    }

}
