package com.example.demo.repository;

import com.example.demo.models.Category;
import com.example.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    @Query("SELECT c FROM Category c WHERE c.user IS NULL OR c.user.id=:user")
    List<Category> findAvailableCategoriesForUser(@Param("user") Long user);
    
    Optional<Category> findByName(String name);

    List<Category> findAllByUser(User user);
}
