package com.example.expense_tracker.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.expense_tracker.BinarySearchTree;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ExpenseController {

    private final BinarySearchTree bst = new BinarySearchTree();

    // Category endpoints
    @PostMapping("/categories/add")
    public String addCategory(@RequestParam String name, @RequestParam(required = false) String note) {
        return bst.addCategory(name, note);
    }

    @GetMapping("/categories/search")
    public String searchCategory(@RequestParam String name) {
        return bst.searchCategory(name);
    }

    @GetMapping("/categories/list")
    public String listCategories() {
        return bst.listCategories();
    }

    // Expense endpoints under a category
    @PostMapping("/categories/expenses/add")
    public String addExpenseToCategory(@RequestParam String categoryName,
                                       @RequestParam String expenseName,
                                       @RequestParam double amount,
                                       @RequestParam String date,
                                       @RequestParam(required = false) String note) {
        return bst.addExpense(categoryName, expenseName, amount, date, note);
    }

    @GetMapping("/categories/expenses/list")
    public String listExpensesInCategory(@RequestParam String categoryName) {
        return bst.listExpenses(categoryName);
    }

    @DeleteMapping("/categories/expenses/delete")
    public String deleteExpenseInCategory(@RequestParam String categoryName,
                                          @RequestParam String expenseName) {
        return bst.deleteExpense(categoryName, expenseName);
    }
}
