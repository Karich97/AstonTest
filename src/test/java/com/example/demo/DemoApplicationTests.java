package com.example.demo;

import com.example.demo.models.BankAccount;
import com.example.demo.models.Transaction;
import com.example.demo.repository.AccountRepository;
import com.example.demo.service.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class DemoApplicationTests {

	@Mock
	private AccountRepository accountRepository;

	@InjectMocks
	private AccountServiceImpl service;

	@Test
	public void testCreateAccount() {
		String beneficiaryName = "John Doe";
		String pinCode = "1234";
		BankAccount account = new BankAccount(beneficiaryName, pinCode);
		when(accountRepository.save(account)).thenReturn(account);

		Long createdAccountId = service.createNewAccount(beneficiaryName, pinCode);

		assertEquals(beneficiaryName, accountRepository.findById(createdAccountId).orElseThrow().getOwnerName());
		assertEquals(pinCode, accountRepository.findById(createdAccountId).orElseThrow().getPinCode());
	}

	@Test
	public void testDeposit() {
		Long accountNumber = 123456789L;
		String pinCode = "1234";
		double amount = 100.0;
		BankAccount account = new BankAccount("John Doe", pinCode);
		account.setId(accountNumber);
		account.setCurrentBalance(0.0);
		when(accountRepository.findById(accountNumber)).thenReturn(Optional.of(account));

		service.deposit(accountNumber, amount);

		assertEquals(amount, account.getCurrentBalance());
	}

	@Test
	public void testWithdraw() {
		Long accountNumber = 123456789L;
		String pinCode = "1234";
		double initialBalance = 100.0;
		double withdrawAmount = 50.0;
		BankAccount account = new BankAccount("John Doe", pinCode);
		account.setId(accountNumber);
		account.setCurrentBalance(initialBalance);
		when(accountRepository.findById(accountNumber)).thenReturn(Optional.of(account));

		service.withdraw(accountNumber, pinCode, withdrawAmount);

		assertEquals(initialBalance - withdrawAmount, account.getCurrentBalance());
	}

	@Test
	public void testTransfer() {
		Long toAccountNumber = 123456789L;
		Long fromAccountNumber = 987654321L;
		String pinCode = "1234";
		double initialBalance = 100.0;
		double transferAmount = 50.0;
		BankAccount fromAccount = new BankAccount("John Doe", pinCode);
		fromAccount.setId(fromAccountNumber);
		fromAccount.setCurrentBalance(initialBalance);
		BankAccount toAccount = new BankAccount("Jane Smith", pinCode);
		toAccount.setId(toAccountNumber);
		toAccount.setCurrentBalance(0.0);
		when(accountRepository.findById(fromAccountNumber)).thenReturn(Optional.of(fromAccount));
		when(accountRepository.findById(toAccountNumber)).thenReturn(Optional.of(toAccount));

		service.transfer(fromAccountNumber, toAccountNumber, pinCode, transferAmount);

		assertEquals(initialBalance - transferAmount, fromAccount.getCurrentBalance());
		assertEquals(transferAmount, toAccount.getCurrentBalance());
	}

	@Test
	public void testGetAllAccounts() {
		List<BankAccount> accounts = new ArrayList<>();
		accounts.add(new BankAccount("John Doe", "1234"));
		accounts.add(new BankAccount("Jane Smith", "5678"));
		when(accountRepository.findAll()).thenReturn(accounts);

		List<BankAccount> retrievedAccounts = service.findAll();

		assertEquals(accounts.size(), retrievedAccounts.size());
	}

	@Test
	public void testGetTransactions() {
		Long accountNumber = 123456789L;
		List<Transaction> transactions = new ArrayList<>();
		transactions.add(new Transaction(accountNumber, 100.0));
		transactions.add(new Transaction(123456789L, 50.0));
		BankAccount account = new BankAccount("John Doe", "1234");
		account.setId(accountNumber);
		account.setTransactions(transactions);
		when(accountRepository.findById(accountNumber)).thenReturn(Optional.of(account));

		List<Transaction> retrievedTransactions = service.getTransactions(accountNumber);

		assertEquals(transactions.size(), retrievedTransactions.size());
	}

}
