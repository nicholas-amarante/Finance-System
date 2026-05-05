package com.example.demo.controller;

import com.example.demo.dto.DashboardDTO;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.service.DashboardService;
import com.example.demo.service.TransactionService;
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
    public ResponseEntity<DashboardDTO.CalculateIncomeAndExpenseResponseByMonth> getDashboard(@RequestParam(required = false) Integer month, @RequestParam(required = false) Integer year){
        if(month==null){
            LocalDate now=LocalDate.now();
            month=now.getMonthValue();
        }
        if(year==null){
            LocalDate now=LocalDate.now();
            year=now.getYear();
        }
        DashboardDTO.CalculateIncomeAndExpenseResponseByMonth summary=dashboardService.getMonthlySummary(month,year);
        return ResponseEntity.ok(summary);
    }

    @GetMapping("by-category")
    public List<DashboardDTO.CalculateIncomeAndExpenseResponseByCategorys> getDashboardByCategorys(@RequestParam(required = false) Integer categoryId){
        return dashboardService.getExpensesByCategorys();
    }
}
