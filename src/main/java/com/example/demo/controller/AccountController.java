package com.example.demo.controller;

import com.example.demo.models.BankAccount;
import com.example.demo.models.Status;
import com.example.demo.models.Transaction;
import com.example.demo.request.RequestAccount;
import com.example.demo.request.TransactionRequest;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("account")
public class AccountController {
    private final AccountService service;

    @Autowired
    public AccountController(AccountService service) {
        this.service = service;
    }

    @GetMapping
    public List<BankAccount> getAllAccounts(){
        return service.findAll();
    }

    @PostMapping("/new")
    public Long createNewAccount(@RequestBody RequestAccount requestAccount){
        return service.createNewAccount(requestAccount.getOwnerName(), requestAccount.getPinCode());
    }

    @GetMapping("/{id}")
    public BankAccount getAccountById(@PathVariable Long id){
        return service.findById(id);
    }

    @GetMapping("/{id}/transaction")
    public List<Transaction> getAccountTransactions(@PathVariable Long id){
        BankAccount account = service.findById(id);
        return account.getTransactions();
    }

    @PostMapping("/account/transaction/deposit")
    public Status deposit(@RequestBody TransactionRequest transactionRequest) {
        return service.deposit(transactionRequest.getToId(), transactionRequest.getAmount());
    }

    @PostMapping("/account/transaction/withdraw")
    public Status withdraw(@RequestBody TransactionRequest transactionRequest) {
        return service.withdraw(transactionRequest.getToId(), transactionRequest.getPinCode(), transactionRequest.getAmount());
    }

    @PostMapping("/account/transaction/transfer")
    public Status transfer(@RequestBody TransactionRequest transactionRequest) {
        return service.transfer(transactionRequest.getFromId(), transactionRequest.getToId(), transactionRequest.getPinCode(), transactionRequest.getAmount());
    }
}
