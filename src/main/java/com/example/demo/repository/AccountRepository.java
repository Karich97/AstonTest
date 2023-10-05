package com.example.demo.repository;

import com.example.demo.models.Account;
import org.springframework.data.repository.Repository;

@org.springframework.stereotype.Repository
public class AccountRepository implements Repository<Integer, Account> {
}
