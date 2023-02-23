package com.microservices.CustomerMicroservice.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.CustomerMicroservice.entity.Customer;
import com.microservices.CustomerMicroservice.repository.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public List<Customer> getAllCustomerDetails() {
		List<Customer> customerList = (List<Customer>) customerRepository.findAll();
		return customerList;
	}

	public Customer saveCustomer(Customer customer) {
		log.info("Inside save Customer method of Customer Service");
		return customerRepository.save(customer);
	}

	public Customer fetchCustomerByEmailId(String email) {
		return customerRepository.fetchCustomerByEmailId(email);
	}

	public Customer fetchUserByEmailAndPassword(String email, String password) {
		return customerRepository.findByEmailAndPassword(email, password);
	}

	public Customer findById(int customerId) {
		return customerRepository.CustomerById(customerId);
	}

}
