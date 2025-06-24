package com.et.expense_tracker.controller;

import com.et.expense_tracker.model.Expense;
import com.et.expense_tracker.repository.ExpenseRepository;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/service/expenses")
public class ExpenseController {

    @Autowired
    @NonNull
    private ExpenseRepository expenseRepository;

    // Get all expenses
    @GetMapping
    public List<Expense> getAllExpenses(){
        return expenseRepository.findAll();
    }

    // Get expense by id
    @GetMapping("/{id}")
    public Expense getExpenseById(@PathVariable Long id){
        return expenseRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No expense found with id: " + id));
    }

    // Create a new expense
    @PostMapping
    public Expense createExpense(@Valid @RequestBody Expense expense){
        return expenseRepository.save(expense);
    }

    // Update an expense
    @PutMapping("/{id}")
    public Expense updateExpense(@PathVariable Long id, @Valid @RequestBody Expense expense){
        Expense existingExpense = expenseRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No expense found with id: " + id));
        existingExpense.setDescription(expense.getDescription());
        existingExpense.setAmount(expense.getAmount());
        existingExpense.setDate(expense.getDate());
        existingExpense.setCategory(expense.getCategory());
        existingExpense.setPaymentMethod(expense.getPaymentMethod());
        return expenseRepository.save(existingExpense);
    }

    // Delete an expense
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable Long id){
        if(!expenseRepository.existsById(id)){
            throw new RuntimeException("No expense found with id: " + id);
        }

        expenseRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
    
    
 
}
