package com.example.SmartSpendExpense.service;

import com.example.SmartSpendExpense.model.Expense;
import com.example.SmartSpendExpense.repositery.ExpenseRepositery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {
    @Autowired
    ExpenseRepositery expenseRepositery;
    public void save(Expense e) {
        expenseRepositery.save(e);
    }

    public void del(Integer id) {
        expenseRepositery.deleteById(id);
    }

    public Optional<Expense> findById(Integer id) {
        return expenseRepositery.findById(id);

    }
    public Page<Expense> findPaginatedByUser(int pageNo, int pageSize, String sortField, String sortDir, Integer userId) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return expenseRepositery.findByUserId(userId, pageable);
    }

    public Page<Expense> findPaginatedByKeywordAndUser(int pageNo, int pageSize, String sortField, String sortDir, String keyword, Integer userId) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return expenseRepositery.searchByKeywordAndUserId(keyword, userId, pageable);
    }
}
