package com.example.SmartSpendExpense.controller;

import com.example.SmartSpendExpense.model.Users;
import com.example.SmartSpendExpense.repositery.ExpenseRepositery;
import com.example.SmartSpendExpense.repositery.UserRepositery;
import com.example.SmartSpendExpense.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchTransactionManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/expense")
public class ChartController {
    @Autowired
    ExpenseService expenseService;
    @Autowired
    private ExpenseRepositery expenseRepository;
    @Autowired
    UserRepositery userRepositery;
    @GetMapping("/index")
    public String index(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        Users user = userRepositery.findByUsername(currentUsername);
        if (user == null) {
            throw new RuntimeException("User not found: " + currentUsername);
        }
        model.addAttribute("data",expenseRepository.findByUserId(user.getId()));
        return "home";
    }

}
