package com.example.demo.service;

import com.example.demo.models.BankAccount;
import com.example.demo.models.Status;
import com.example.demo.models.Transaction;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository repository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository repository, TransactionRepository transactionRepository) {
        this.repository = repository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<BankAccount> findAll() {
        return repository.findAll();
    }

    @Override
    public BankAccount findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Long createNewAccount(String ownerName, String pinCode) {
        BankAccount account = new BankAccount(ownerName, pinCode);
        return repository.save(account).getId();
    }

    @Override
    public Status deposit(Long toId, Double amount) {
        Transaction transaction = new Transaction(toId, amount);
        try{
            BankAccount account = repository.findById(toId).orElseThrow();
            account.setCurrentBalance(account.getCurrentBalance() + amount);
            transaction.setStatus(Status.APPROVED);
            transactionRepository.save(transaction);
            account.getTransactions().add(transaction);
            repository.save(account);
        }catch (NoSuchElementException ex){
            System.out.println(ex.getMessage());
            transactionRepository.save(transaction);
            transaction.setStatus(Status.DECLINE);
        }
        return transaction.getStatus();
    }

    @Override
    public Status withdraw(Long fromId, String pinCode, Double amount) {
        Transaction transaction = new Transaction(fromId, -amount);
        try{
            BankAccount account = repository.findById(fromId).orElseThrow();
            if (account.getPinCode().equals(pinCode) && account.getCurrentBalance() >= amount){
                account.setCurrentBalance(account.getCurrentBalance() - amount);
                transaction.setStatus(Status.APPROVED);
                transactionRepository.save(transaction);
                repository.save(account);
            }else {
                System.out.println("not enough money or wrong PIN");
                transaction.setStatus(Status.DECLINE);
                transactionRepository.save(transaction);
            }
            account.getTransactions().add(transaction);
            repository.save(account);
        }catch (NoSuchElementException ex){
            System.out.println(ex.getMessage());
            transaction.setStatus(Status.DECLINE);
            transactionRepository.save(transaction);
        }
        return transaction.getStatus();
    }

    @Override
    public Status transfer(Long fromId, Long toId, String pinCode, Double amount) {
        Optional<BankAccount> fromAccount = repository.findById(fromId);
        Optional<BankAccount> toAccount = repository.findById(fromId);
        if (fromAccount.isPresent() && toAccount.isPresent()){
            if (withdraw(fromId, pinCode, amount) == Status.APPROVED){
                return deposit(toId, amount);
            }
        }
        return Status.DECLINE;
    }

    @Override
    public List<Transaction> getTransactions(Long accountNumber) {
        List<Transaction> transactionList = new ArrayList<>();
        try {
            transactionList = repository.findById(accountNumber).orElseThrow().getTransactions();
        }catch (NoSuchElementException ex){
            System.out.println(ex.getMessage());
        }
        return transactionList;
    }
}
