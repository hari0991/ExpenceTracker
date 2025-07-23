package com.example.SmartSpendExpense.service;

import com.example.SmartSpendExpense.model.Users;
import com.example.SmartSpendExpense.repositery.UserRepositery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepositery userRepositery;

    BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();


    public void saveuser(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepositery.save(user);
    }
}
