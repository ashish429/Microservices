package com.microservices.AccountMicroservice.valueObjects;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

	private int customerId;
	private String customerName;
	private String email;
	private Date dob;
	private String customerPassword;

}
