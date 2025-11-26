package com.example.demo.service;

import com.example.demo.dto.CreateTransactionDTO;
import com.example.demo.models.Account;
import com.example.demo.models.CreditCard;
import com.example.demo.models.Transaction;
import com.example.demo.models.User;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CreditCardRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository; // Precisa ter criado esse Repository
    @Autowired
    private CreditCardRepository creditCardRepository; // Precisa ter criado esse Repository

    public Transaction create(CreateTransactionDTO dto) {
        // 1. Buscar o Usuário (Obrigatório)
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // 2. Buscar Conta (Opcional - pode ser nulo se for gasto no cartão direto, dependendo da regra)
        Account account = null;
        if (dto.accountId() != null) {
            account = accountRepository.findById(dto.accountId()) // O método findById retorna Optional, não precisa criar nada no repo
                    .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        }

        // 3. Buscar Cartão (Opcional)
        CreditCard creditCard = null;
        if (dto.creditCardId() != null) {
            creditCard = creditCardRepository.findById(dto.creditCardId()) // O método findById retorna Optional
                    .orElseThrow(() -> new RuntimeException("Cartão não encontrado"));
        }

        // 4. Montar a Transação
        Transaction transaction = Transaction.builder()
                .name(dto.name())
                .value(dto.value())
                .description(dto.description())
                .date(dto.date())
                .type(dto.type())
                .user(user)
                .account(account)
                .creditCard(creditCard)
                .destinationId(dto.destinationId()) // Apenas salva o ID por enquanto
                .build();

        return transactionRepository.save(transaction);
    }

    public List<Transaction> listAllByUser(Long userId) {
        return transactionRepository.findByUserId(userId);
    }
}