package com.example.expense_tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExpenseTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseTrackerApplication.class, args);
	}

}

// Define a BSTNode class to represent each node in the tree
class BSTNode {
    String name;
    double amount;
    BSTNode left, right;

    public BSTNode(String name, double amount) {
        this.name = name;
        this.amount = amount;
        this.left = this.right = null;
    }
}
