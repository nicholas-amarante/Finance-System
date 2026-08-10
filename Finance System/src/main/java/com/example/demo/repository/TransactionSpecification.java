package com.example.demo.repository;

import com.example.demo.models.Transaction;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class TransactionSpecification {

    public static Specification<Transaction> hashUser(Long userId){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("user").get("id"), userId);
    }

    public static Specification<Transaction> hashDescription(String description){
        return (root, query, criteriaBuilder) -> description==null || description.isBlank()?null:
                criteriaBuilder.like(root.get("description"), "%"+description.toLowerCase()+"%");
    }

    public static Specification<Transaction> hashType(String typeName){
        return (root, query, criteriaBuilder) -> typeName==null||typeName.isBlank()?null:
                criteriaBuilder.equal(root.get("transactionType").get("name"), typeName);
    }

    public static Specification<Transaction> hashCategory(String categoryName){
        return (root, query, criteriaBuilder) -> categoryName==null||categoryName.isBlank()?null:
                criteriaBuilder.equal(root.get("category").get("name"), categoryName);
    }

    public static Specification<Transaction> hashAccount(Long accountId){
        return (root, query, criteriaBuilder) -> accountId==null?null:
                criteriaBuilder.equal(root.get("account").get("id"), accountId);
    }

    public static Specification<Transaction> isBetweenDates(LocalDateTime startDate, LocalDateTime endDate){
        return (root, query, criteriaBuilder) -> {
            if (startDate == null && endDate == null) return null;
            if (startDate!=null && endDate==null) return criteriaBuilder.greaterThanOrEqualTo(root.get("dateTime"), startDate);
            if (startDate==null) return criteriaBuilder.lessThanOrEqualTo(root.get("dateTime"), endDate);
            return criteriaBuilder.between(root.get("dateTime"), startDate, endDate);
        };
    }
}
