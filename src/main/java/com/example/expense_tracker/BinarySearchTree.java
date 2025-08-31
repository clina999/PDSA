package com.example.expense_tracker;

// Binary search tree that stores categories. Each category holds a linked list of expenses.
public class BinarySearchTree {
    private Category root;

    // Add or update a category
    public String addCategory(String name, String note) {
        root = addCategoryRec(root, name, note);
        return "Category added/updated: " + name;
    }

    private Category addCategoryRec(Category node, String name, String note) {
        if (node == null) {
            return new Category(name, note);
        }
        int cmp = name.compareTo(node.name);
        if (cmp < 0) {
            node.left = addCategoryRec(node.left, name, note);
        } else if (cmp > 0) {
            node.right = addCategoryRec(node.right, name, note);
        } else {
            // category exists, update note
            node.note = note;
        }
        return node;
    }

    // Search for a category and return a string summary
    public String searchCategory(String name) {
        Category node = searchCategoryRec(root, name);
        if (node == null) {
            return "Category not found: " + name;
        }
        int count = countExpenses(node);
        return "Category: " + node.name + " | note: " + node.note + " | expenses: " + count;
    }

    private Category searchCategoryRec(Category node, String name) {
        if (node == null) return null;
        int cmp = name.compareTo(node.name);
        if (cmp == 0) return node;
        if (cmp < 0) return searchCategoryRec(node.left, name);
        return searchCategoryRec(node.right, name);
    }

    // List all categories (in-order)
    public String listCategories() {
        StringBuilder sb = new StringBuilder();
        listCategoriesRec(root, sb);
        return sb.toString();
    }

    private void listCategoriesRec(Category node, StringBuilder sb) {
        if (node == null) return;
        listCategoriesRec(node.left, sb);
        sb.append(node.name).append(": ").append(node.note == null ? "" : node.note).append("\n");
        listCategoriesRec(node.right, sb);
    }

    // Add expense to a specific category
    public String addExpense(String categoryName, String expenseName, double amount, String date, String note) {
        Category cat = searchCategoryRec(root, categoryName);
        if (cat == null) {
            return "Category not found: " + categoryName;
        }
        Expense newExpense = new Expense(expenseName, amount, date, note);
        // use LinkedList helper to insert at head
        cat.expensesHead = LinkedList.insertAtHead(cat.expensesHead, newExpense);
        return "Expense added to " + categoryName + ": " + expenseName + " - " + amount;
    }

    // List expenses for a category
    public String listExpenses(String categoryName) {
        Category cat = searchCategoryRec(root, categoryName);
        if (cat == null) {
            return "Category not found: " + categoryName;
        }
        if (cat.expensesHead == null) return "No expenses for category: " + categoryName;
        return LinkedList.listToString(cat.expensesHead);
    }

    // Delete an expense by name from a category
    public String deleteExpense(String categoryName, String expenseName) {
        Category cat = searchCategoryRec(root, categoryName);
        if (cat == null) {
            return "Category not found: " + categoryName;
        }
        // check if expense exists
        Expense found = LinkedList.findByName(cat.expensesHead, expenseName);
        if (found == null) {
            return "Expense not found: " + expenseName + " in category " + categoryName;
        }
        cat.expensesHead = LinkedList.removeByName(cat.expensesHead, expenseName);
        return "Expense deleted: " + expenseName + " from " + categoryName;
    }

    private int countExpenses(Category cat) {
        return LinkedList.count(cat.expensesHead);
    }

    // Sort expenses in a category in-place using the linked-list sort helpers.
    // sortBy: "name" or "amount" (defaults to name). order: "asc" or "desc" (defaults to asc).
    public String sortExpenses(String categoryName, String sortBy, String order) {
        Category cat = searchCategoryRec(root, categoryName);
        if (cat == null) {
            return "Category not found: " + categoryName;
        }
        if (cat.expensesHead == null) return "No expenses for category: " + categoryName;

        boolean asc = !"desc".equalsIgnoreCase(order);
        if ("amount".equalsIgnoreCase(sortBy)) {
            cat.expensesHead = LinkedList.sortByAmount(cat.expensesHead, asc);
        } else {
            cat.expensesHead = LinkedList.sortByName(cat.expensesHead, asc);
        }

        return LinkedList.listToString(cat.expensesHead);
    }
}
