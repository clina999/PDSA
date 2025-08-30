package com.example.expense_tracker;

// Package-private helper utilities for Expense linked lists
class LinkedListHelper {

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
}
