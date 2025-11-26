package com.example.demo.repository;

import com.example.demo.models.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
    // Mude de 'public class' para 'public interface' e estenda JpaRepository
}