package com.microservices.AccountMicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.AccountMicroservice.entity.Account;
import com.microservices.AccountMicroservice.service.AccountService;
import com.microservices.AccountMicroservice.valueObjects.ResponseTemplate;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/accounts")
@Slf4j
public class AccountController {

	@Autowired
	private AccountService accountService;

	// INSERT CUSTOMER DATA
	@PostMapping("/")
	public Account saveAccount(@RequestBody Account account) throws Exception {
		log.info("Inside saveAccount of accountController");
		return accountService.saveAccount(account);
	}

	@GetMapping("/{id}")
	public ResponseTemplate getCustomerWithAccount(@PathVariable("id") int accountId) {
		log.info("Inside getCustomerWithAccount of accountController");
		return accountService.getCustomerWithAccount(accountId);
	}

	@PutMapping(value = "/")
	public Account updateAccount(@RequestBody Account account) {
		double totalAmount = account.getSavings();
		double addMoney = account.getAddMoney();

		totalAmount = totalAmount + addMoney;
		account.setSavings(totalAmount);
		return account;
	}

	@PutMapping(value = "/addMoney")
	public Account totalAddedMoney(@RequestBody Account account) {
		double totalAmount = account.getSavings();
		double addMoney = account.getAddMoney();

		totalAmount = totalAmount + addMoney;
		account.setSavings(totalAmount);
		return account;
	}

	@PutMapping(value = "/withdrawMoney")
	public Account totalMoneyLeft(@RequestBody Account account) {
		double totalAmount = account.getSavings();
		double withdrawMoney = account.getWithdrawMoney();

		totalAmount = totalAmount - withdrawMoney;
		account.setSavings(totalAmount);
		return account;
	}
}
