package com.example.demo.models;

import java.util.List;

public class Account {
    private int id;
    private int currentBalance;
    private List<Transaction> transactions;

    public Account(int id, int currentBalance, List<Transaction> transactions) {
        this.id = id;
        this.currentBalance = currentBalance;
        this.transactions = transactions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(int currentBalance) {
        this.currentBalance = currentBalance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
