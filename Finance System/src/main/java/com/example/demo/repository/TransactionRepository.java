package com.example.demo.repository;

import com.example.demo.models.Transaction;
import com.example.demo.models.TransactionTypeClass;
import com.example.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT COALESCE(SUM(t.value), 0) FROM Transaction t "+
    "WHERE t.user=:user "+
    "AND t.dateTime BETWEEN :startDate AND :endDate "+
    "AND t.transactionType = :type")
    BigDecimal getSumByUserAndDateAndType(@Param("user") User user, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("type") TransactionTypeClass type);

    TransactionTypeClass findByName(String name);
}
