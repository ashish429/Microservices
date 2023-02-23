package com.microservices.AccountMicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.AccountMicroservice.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{

	Account findByAccountId(int accountId);
	

}
