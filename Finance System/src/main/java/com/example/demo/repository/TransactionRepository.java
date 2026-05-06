package com.example.demo.repository;

import com.example.demo.dto.DashboardDTO;
import com.example.demo.models.Transaction;
import com.example.demo.models.TransactionTypeClass;
import com.example.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT COALESCE(SUM(t.value), 0) FROM Transaction t "+
    "WHERE t.user=:user "+
    "AND t.dateTime BETWEEN :startDate AND :endDate "+
    "AND t.transactionType = :type")

    BigDecimal getSumByUserAndDateAndType(@Param("user") User user, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("type") TransactionTypeClass type);

    @Query("select new com.example.demo.dto.DashboardDTO$CalculateIncomeAndExpenseByCategorysResponse(c.name, sum(t.value))"+
    "from Transaction t join t.category c "+
    "where t.user=:user "+
    "and t.transactionType.name='EXPENSE' "+
    "and t.dateTime between :startDate and :endDate "+
    "group by c.name")

    List<DashboardDTO.CalculateIncomeAndExpenseByCategorysResponse> getExpensesByCategory(@Param("user") User user, @Param("startDate") LocalDateTime startDate, @Param("endDate")LocalDateTime endDate);

    @Query("select new com.example.demo.dto.DashboardDTO$CalculateIncomeAndExpenseByPeriodsResponse(" +
            "t.id, t.name, t.description, t.value, c.name, t.transactionType.name, t.dateTime) " +
            "from Transaction t join t.category c "+
            "where t.user=:user "+
            "order by t.dateTime desc")

    List<DashboardDTO.CalculateIncomeAndExpenseByPeriodsResponse> getLastTenTransactions(@Param("user") User user, Pageable pageable);

    TransactionTypeClass findByName(String name);
}
