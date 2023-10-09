package com.example.demo.service;

import com.example.demo.models.Status;
import com.example.demo.models.Transaction;

import java.util.List;

public interface TransactionService {
    Status deposit(Long toId, Double amount);

    Status withdraw(Long toId, String pinCode, Double amount);

    Status transfer(Long fromId, Long toId, String pinCode, Double amount);

    List<Transaction> getTransactions(Long accountNumber);
}
