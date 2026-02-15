package com.example.demo.controller;

import com.example.demo.dto.DashboardDTO;
import com.example.demo.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    @Autowired
    private DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<DashboardDTO> getDashboard(@RequestParam(required = false) Integer month, @RequestParam(required = false) Integer year){
        if(month==null){
            LocalDate now=LocalDate.now();
            month=now.getMonthValue();
        }
        if(year==null){
            LocalDate now=LocalDate.now();
            year=now.getYear();
        }
        DashboardDTO summary=dashboardService.getMonthlySummary(month,year);
        return ResponseEntity.ok(summary);
    }
}
