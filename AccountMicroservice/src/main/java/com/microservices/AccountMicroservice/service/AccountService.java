package com.microservices.AccountMicroservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservices.AccountMicroservice.entity.Account;
import com.microservices.AccountMicroservice.repository.AccountRepository;
import com.microservices.AccountMicroservice.valueObjects.Customer;
import com.microservices.AccountMicroservice.valueObjects.ResponseTemplate;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private RestTemplate restTemplate;

	public Account saveAccount(Account account) {
		log.info("Inside saveAccount of accountController");
		return accountRepository.save(account);
	}

	public ResponseTemplate getCustomerWithAccount(int accountId) {
		log.info("Inside getCustomerWithAccount of accountController");
		ResponseTemplate rt = new ResponseTemplate();
		Account account = accountRepository.findByAccountId(accountId);
		Customer customer = restTemplate.getForObject("http://CUSTOMER-MICROSERVICES/customers/" + account.getCutomerId(),
				Customer.class);
		rt.setAccount(account);
		rt.setCustomer(customer);
		return rt;
	}

}
