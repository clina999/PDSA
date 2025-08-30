package com.example.expense_tracker;

// BST node representing a category which holds a linked list of Expense
class Category {
    String name;
    String note;
    Expense expensesHead; // head of linked list of expenses
    Category left, right;

    Category(String name, String note) {
        this.name = name;
        this.note = note;
        this.expensesHead = null;
        this.left = this.right = null;
    }

    @Override
    public String toString() {
        return name + ": " + (note == null ? "" : note);
    }
}
