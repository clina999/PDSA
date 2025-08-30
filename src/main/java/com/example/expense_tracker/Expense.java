package com.example.expense_tracker;

// Singly-linked list node representing an expense
class Expense {
    String name;
    double amount;
    String date;
    String note;
    Expense next;

    Expense(String name, double amount, String date, String note) {
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.note = note;
        this.next = null;
    }

    @Override
    public String toString() {
        return name + " : " + amount + " | " + date + " | " + (note == null ? "" : note);
    }
}
