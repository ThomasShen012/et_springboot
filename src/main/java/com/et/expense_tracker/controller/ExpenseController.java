package com.et.expense_tracker.controller;

import com.et.expense_tracker.model.Expense;
import com.et.expense_tracker.repository.ExpenseRepository;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/service")
public class ExpenseController {

    @Autowired
    private ExpenseRepository expenseRepository;

    //Get all expenses
    @GetMapping("/get-expenses")
    public List<Expense> getAllExpenses(){
        return expenseRepository.findAll();
    }

    //Get expense by id
    @GetMapping("/get-expense/{id}")
    public Expense getExpenseById(@PathVariable Long id){
        if(!expenseRepository.existsById(id)){
            throw new RuntimeException("Expense not found");
        }
        return expenseRepository.findById(id).get();
    }

    //Create a new expense
    @PostMapping("/add-expense")
    /*
    public Expense createExpense(@RequestParam String description, @RequestParam Double amount, @RequestParam String category){
        Expense expense = new Expense();
        expense.setDescription(description);
        expense.setAmount(amount);
        expense.setCategory(category);
        expense.setDate(LocalDateTime.now());
        expenseRepository.save(expense);
    */
    public Expense createExpense(@RequestBody Expense expense){
        expenseRepository.save(expense);
        return expense;
    }

    //Update an expense
    @PutMapping("/update-expense/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @RequestBody Expense expense){

        Expense existingExpense = expenseRepository.findById(id).orElseThrow(() -> new RuntimeException("Expense not found"));
        
        existingExpense.setDescription(expense.getDescription());
        existingExpense.setAmount(expense.getAmount());
        existingExpense.setDate(expense.getDate());
        existingExpense.setCategory(expense.getCategory());
      
        return ResponseEntity.ok(expenseRepository.save(existingExpense));

    }
    
    
 
}
