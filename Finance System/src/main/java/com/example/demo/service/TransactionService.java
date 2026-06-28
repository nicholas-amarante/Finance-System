package com.example.demo.service;

import com.example.demo.dto.CreateTransactionDTO;
import com.example.demo.dto.DashboardDTO;
import com.example.demo.dto.PagedDTO;
import com.example.demo.dto.TransactionDTO;
import com.example.demo.models.*;
import com.example.demo.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.data.jpa.domain.Specification.allOf;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionTypeRepository transactionTypeRepository;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private CategoryRepository categoryRepository;

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
        Category category=categoryRepository.findByName(createTransactionDTO.category())
                .orElseThrow(()->new RuntimeException("Category não encontrado "+createTransactionDTO.category()));
        transaction.setCategory(category);
        transactionRepository.save(transaction);

        if(createTransactionDTO.transactionType().equals(TransactionType.INCOME)){
            account.setCurrentBalance(account.getCurrentBalance().add(transaction.getValue()));
        }else{
            account.setCurrentBalance(account.getCurrentBalance().subtract(transaction.getValue()));
        }
        accountRepository.save(account);
    }

    public PagedDTO.PagedResponse<TransactionDTO.TransactionFeed> getFilteredTransaction(User loggedUser, String description, String type, Long accountId, LocalDateTime startDate, LocalDateTime endDate, int pageNumber, int pageSize){
        Pageable pageable= PageRequest.of(pageNumber, pageSize, Sort.by("dateTime").descending());
        Specification<Transaction> spec=allOf(
                TransactionSpecification.hashDescription(description),
                TransactionSpecification.hashAccount(accountId),
                TransactionSpecification.hashType(type),
                TransactionSpecification.isBetweenDates(startDate, endDate)
        );
        Page<Transaction> transactionPage=transactionRepository.findAll(spec, pageable);
        List<TransactionDTO.TransactionFeed> dtoList=transactionPage.getContent().stream()
                .map(t-> new TransactionDTO.TransactionFeed(t.getId(), t.getName(), t.getDescription(), t.getValue(), t.getCategory().getName(), t.getTransactionType().getName(), t.getDateTime()))
                .toList();

        return new PagedDTO.PagedResponse<>(dtoList, transactionPage.getNumber(), transactionPage.getSize(), transactionPage.getTotalElements(), transactionPage.getTotalPages(), transactionPage.isLast());
    }
}
