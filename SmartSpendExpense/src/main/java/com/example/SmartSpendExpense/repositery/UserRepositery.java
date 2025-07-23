package com.example.SmartSpendExpense.repositery;

import com.example.SmartSpendExpense.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositery extends JpaRepository<Users,Integer> {
    Users findByUsername(String username);
}
