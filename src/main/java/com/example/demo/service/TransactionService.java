package com.example.demo.service;

import com.example.demo.dto.PagedDTO;
import com.example.demo.dto.TransactionDTO;
import com.example.demo.models.*;
import com.example.demo.repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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
    public TransactionDTO.ResponseTransactionDTO createTransaction(@RequestBody @Valid TransactionDTO.CreateTransactionDTO createTransactionDTO){
        User currentUser=authenticationService.getLoggedUser();
        Account account=accountRepository.findByIdAndUser(createTransactionDTO.account_id(), currentUser)
                        .orElseThrow(()->new EntityNotFoundException("Conta não encontrada ou não pertence ao usuário"));
        TransactionTypeClass transactionTypeClass=transactionTypeRepository.findByName(createTransactionDTO.transactionType())
                .orElseThrow(()-> new EntityNotFoundException("Tipo de transação não encontrado: "+createTransactionDTO.transactionType()));
        Category category=categoryRepository.findByNameAndUserOrNameAndUserIsNull(createTransactionDTO.category(), currentUser, createTransactionDTO.category())
                .orElseThrow(()->new EntityNotFoundException("Categoria não encontrada: "+createTransactionDTO.category()));
        LocalDateTime transactionDateTime=createTransactionDTO.dateTime()!=null
                ?createTransactionDTO.dateTime()
                :LocalDateTime.now();

        Transaction transaction=new Transaction();
        transaction.setName(createTransactionDTO.name());
        transaction.setDescription(createTransactionDTO.description());
        transaction.setValue(createTransactionDTO.value());
        transaction.setDateTime(transactionDateTime);
        transaction.setUser(currentUser);
        transaction.setAccount(account);
        transaction.setTransactionType(transactionTypeClass);
        transaction.setCategory(category);
        transactionRepository.save(transaction);

        if(TransactionType.INCOME.equals(createTransactionDTO.transactionType())){
            account.setCurrentBalance(account.getCurrentBalance().add(transaction.getValue()));
        }else{
            account.setCurrentBalance(account.getCurrentBalance().subtract(transaction.getValue()));
        }
        accountRepository.save(account);
        return TransactionDTO.ResponseTransactionDTO.fromEntity(transaction);
    }

    public PagedDTO.PagedResponse<TransactionDTO.TransactionFeed> getFilteredTransaction(User loggedUser, String description, String type, String category, Long accountId, LocalDateTime startDate, LocalDateTime endDate, int pageNumber, int pageSize){
        Pageable pageable= PageRequest.of(pageNumber, pageSize, Sort.by("dateTime").descending());
        Specification<Transaction> spec=allOf(
                TransactionSpecification.hashDescription(description),
                TransactionSpecification.hashAccount(accountId),
                TransactionSpecification.hashType(type),
                TransactionSpecification.hashCategory(category),
                TransactionSpecification.isBetweenDates(startDate, endDate)
        );
        Page<Transaction> transactionPage=transactionRepository.findAll(spec, pageable);
        List<TransactionDTO.TransactionFeed> dtoList=transactionPage.getContent().stream()
                .map(t-> new TransactionDTO.TransactionFeed(t.getId(), t.getName(), t.getDescription(), t.getValue(), t.getCategory().getName(), t.getTransactionType().getName(), t.getDateTime(), t.getAccount().getBank().getName()))
                .toList();



        return new PagedDTO.PagedResponse<>(dtoList, transactionPage.getNumber(), transactionPage.getSize(), transactionPage.getTotalElements(), transactionPage.getTotalPages(), transactionPage.isLast());
    }
}
