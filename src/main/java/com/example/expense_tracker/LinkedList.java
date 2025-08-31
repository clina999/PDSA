package com.example.expense_tracker;

import java.util.Comparator;

// Public helper utilities for Expense linked lists
public class LinkedList {

    // Insert an Expense node at the head of the list and return new head
    static Expense insertAtHead(Expense head, Expense newExpense) {
        if (newExpense == null) return head;
        newExpense.next = head;
        return newExpense;
    }

    // Convert the linked list to a single string (each expense on its own line)
    static String listToString(Expense head) {
        if (head == null) return "";
        StringBuilder sb = new StringBuilder();
        Expense cur = head;
        while (cur != null) {
            sb.append(cur.toString()).append("\n");
            cur = cur.next;
        }
        return sb.toString();
    }

    // Count nodes in the list
    static int count(Expense head) {
        int c = 0;
        Expense cur = head;
        while (cur != null) {
            c++;
            cur = cur.next;
        }
        return c;
    }

    // Find an expense by name (returns the node or null)
    static Expense findByName(Expense head, String name) {
        Expense cur = head;
        while (cur != null) {
            if (cur.name != null && cur.name.equals(name)) return cur;
            cur = cur.next;
        }
        return null;
    }

    // Remove the first expense with matching name and return the new head
    static Expense removeByName(Expense head, String name) {
        if (head == null) return null;
        if (head.name != null && head.name.equals(name)) return head.next;
        Expense prev = head;
        Expense cur = head.next;
        while (cur != null) {
            if (cur.name != null && cur.name.equals(name)) {
                prev.next = cur.next;
                return head;
            }
            prev = cur;
            cur = cur.next;
        }
        return head;
    }

    // Sort a singly-linked list of Expense using insertion sort and a Comparator.
    // Returns the new head of the sorted list. Comparator defines ascending order.
    static Expense sort(Expense head, Comparator<Expense> cmp) {
        if (head == null || head.next == null) return head;
        Expense sorted = null;
    Expense cur = head;
        while (cur != null) {
            Expense next = cur.next;
            // insert cur into sorted list at correct position
            if (sorted == null || cmp.compare(cur, sorted) < 0) {
                cur.next = sorted;
                sorted = cur;
            } else {
                Expense p = sorted;
                while (p.next != null && cmp.compare(p.next, cur) <= 0) {
                    p = p.next;
                }
                cur.next = p.next;
                p.next = cur;
            }
            cur = next;
        }
        return sorted;
    }
    
    // Convenience: sort by name ascending or descending
    static Expense sortByName(Expense head, boolean ascending) {
        Comparator<Expense> cmp = (a, b) -> a.name == null ? (b.name == null ? 0 : -1) : a.name.compareTo(b.name);
        if (!ascending) cmp = cmp.reversed();
        return sort(head, cmp);
    }

    // Convenience: sort by amount ascending or descending
    static Expense sortByAmount(Expense head, boolean ascending) {
        Comparator<Expense> cmp = (a, b) -> Double.compare(a.amount, b.amount);
        if (!ascending) cmp = cmp.reversed();
        return sort(head, cmp);
    }
}
