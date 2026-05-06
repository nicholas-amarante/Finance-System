package com.example.demo.controller;

import com.example.demo.dto.DashboardDTO;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    @Autowired
    private DashboardService dashboardService;
    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("by-monthly")
    public ResponseEntity<DashboardDTO.CalculateIncomeAndExpenseByMonthResponse> getDashboard(@RequestParam(required = false) Integer month, @RequestParam(required = false) Integer year){
        if(month==null){
            LocalDate now=LocalDate.now();
            month=now.getMonthValue();
        }
        if(year==null){
            LocalDate now=LocalDate.now();
            year=now.getYear();
        }
        DashboardDTO.CalculateIncomeAndExpenseByMonthResponse summary=dashboardService.getMonthlySummary(month,year);
        return ResponseEntity.ok(summary);
    }

    @GetMapping("by-category")
    public List<DashboardDTO.CalculateIncomeAndExpenseByCategorysResponse> getDashboardByCategorys(@RequestParam(required = false) Integer month, @RequestParam(required = false) Integer year){
        return dashboardService.getExpensesByCategorys(month, year);
    }

    @GetMapping("ten-last")
    public List<DashboardDTO.CalculateIncomeAndExpenseByPeriodsResponse> getDashboardByPeriods(@RequestParam(required = false) Integer periodId){
        return dashboardService.fetchLastTenTransactions();
    }
}
