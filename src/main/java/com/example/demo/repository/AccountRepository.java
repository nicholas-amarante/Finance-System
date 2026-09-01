package com.example.demo.repository;

import com.example.demo.models.Account;
import com.example.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    List<Account> findByUser(User user);
    Optional<Account> findByIdAndUser(Long id, User user);

    @Query("select coalesce(sum(a.currentBalance), 0) from Account a where a.user = :user ")
    BigDecimal getTotalCurrentBalanceByUser(@Param("user") User user);
}
