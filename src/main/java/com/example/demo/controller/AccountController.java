package com.example.demo.controller;

import com.example.demo.models.BankAccount;
import com.example.demo.request.RequestAccount;
import com.example.demo.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService service;

    @GetMapping
    public List<BankAccount> getAllAccounts(){
        return service.findAll();
    }

    @PostMapping("/account")
    public Long createNewAccount(@RequestBody RequestAccount requestAccount){
        return service.createNewAccount(requestAccount.getOwnerName(), requestAccount.getPinCode());
    }

    @GetMapping("/{id}")
    public BankAccount getAccountById(@PathVariable Long id){
        return service.findById(id);
    }
}
