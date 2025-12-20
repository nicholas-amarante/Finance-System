package com.example.demo.service;

import com.example.demo.dto.CreateBankDTO;
import com.example.demo.models.Bank;
import com.example.demo.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@Service
public class BankService {
    @Autowired
    private BankRepository bankRepository;

    public void createBank(@RequestBody CreateBankDTO createBankDTO) {
        if(bankRepository.existsBankByName(createBankDTO.name())){
            throw new RuntimeException("Banco já cadastrado:"+createBankDTO.name());
        }
        Bank bank =Bank.builder()
                .name(createBankDTO.name())
                .build();
        bankRepository.save(bank);
    }

    public List<Bank> findAllBank() {
        return bankRepository.findAll();
    }
}
