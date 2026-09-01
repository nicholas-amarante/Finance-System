package com.example.demo.repository;

import com.example.demo.models.TransactionType;
import com.example.demo.models.TransactionTypeClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionTypeRepository extends JpaRepository<TransactionTypeClass,Long> {
    Optional<TransactionTypeClass> findByName(TransactionType name);
}
