package com.example.demo.service;

import com.example.demo.models.BankAccount;
import com.example.demo.models.Status;

import java.util.List;


public interface AccountService {

    List<BankAccount> findAll();

    BankAccount findById(Long id);

    Long createNewAccount(String ownerName, String pinCode);

    Status deposit(Long toId, Double amount);

    Status withdraw(Long toId, String pinCode, Double amount);

    Status transfer(Long fromId, Long toId, String pinCode, Double amount);
}
