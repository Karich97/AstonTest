package com.example.demo.controller;

import com.example.demo.models.Account;
import com.example.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class AccountController {
    AccountRepository repository;

    @Autowired
    public AccountController(AccountRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String getAllUsers(){
        return "HelloController";
    }

    @GetMapping("/account")
    public List<Account> getAllAccounts(){
        List<Account> list = new ArrayList<>();
        return list;
    }
}
