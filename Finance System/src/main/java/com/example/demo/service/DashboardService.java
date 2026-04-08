package com.example.demo.service;

import com.example.demo.dto.DashboardDTO;
import com.example.demo.models.TransactionType;
import com.example.demo.models.TransactionTypeClass;
import com.example.demo.repository.TransactionTypeRepository;
import com.example.demo.models.User;
import com.example.demo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;

@Service
public class DashboardService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private TransactionTypeRepository transactionTypeRepository;

    public DashboardDTO getMonthlySummary(int month, int year){
        User user=authenticationService.getLoggedUser();
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDateTime startDate = yearMonth.atDay(1).atStartOfDay();
        LocalDateTime endDate = yearMonth.atEndOfMonth().atTime(LocalTime.MAX);
        TransactionTypeClass transactionTypeClassIncome=transactionTypeRepository.findByName(TransactionType.INCOME)
                .orElseThrow(()->new RuntimeException("Tipo INCOME não encontrado!"));
        TransactionTypeClass transactionTypeClassExpense=transactionTypeRepository.findByName(TransactionType.EXPENSE)
                .orElseThrow(()->new RuntimeException("Tio EXPENSE não encontrado!"));

        BigDecimal income=transactionRepository.getSumByUserAndDateAndType(user, startDate, endDate, transactionTypeClassIncome);
        if(income==null){income=BigDecimal.ZERO;}
        BigDecimal expense=transactionRepository.getSumByUserAndDateAndType(user, startDate, endDate, transactionTypeClassExpense);
        if(expense==null){expense=BigDecimal.ZERO;}
        BigDecimal balance=income.subtract(expense);
        return new DashboardDTO(income, expense, balance);
    }
}

