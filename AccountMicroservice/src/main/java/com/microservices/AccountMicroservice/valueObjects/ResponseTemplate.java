package com.microservices.AccountMicroservice.valueObjects;

import com.microservices.AccountMicroservice.entity.Account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplate { 
	
	//ResponseTemplate is used to return type Account as well as Customer
	
	private Account account;
	private Customer customer;
	

}
