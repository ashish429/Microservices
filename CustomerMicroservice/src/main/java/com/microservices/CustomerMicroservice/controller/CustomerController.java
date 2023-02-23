package com.microservices.CustomerMicroservice.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.microservices.CustomerMicroservice.entity.Customer;
import com.microservices.CustomerMicroservice.repository.CustomerRepository;
import com.microservices.CustomerMicroservice.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerService customerService;

	// INSERT CUSTOMER DATA
	@PostMapping("/")
	public Customer saveCustomer(@RequestBody Customer customer) throws Exception {
		String tempEmailId = customer.getEmail();
		if (tempEmailId != null && !"".equals(tempEmailId)) {
			Customer customerObj = customerService.fetchCustomerByEmailId(tempEmailId);
			if (customerObj != null) {
				throw new Exception("Customer with " + tempEmailId + " is already exist");
			}
		}
		Customer customerObj1 = null;
		customerObj1 = (customerService).saveCustomer(customer);
		log.info("New Customer data is added");
		return customerObj1;
	}

	// GET ALL CUSTOMER
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public List<Customer> getCustomerDetails() {
		List<Customer> customerList = customerService.getAllCustomerDetails();
		log.info("These are the all customers");
		return customerList;
	}

	// GET CUSTOMER BY ID
	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomerByAccountNumber(@PathVariable("id") int customerId) throws Exception {
		Customer customer = customerService.findById(customerId);
		if (customer != null) {
			throw new Exception("Such Customer is not present");
		}
		log.info("Customer with" + customerId + "are :");
		return ResponseEntity.ok(customer);
	}

	// UPDATE CUSTOMER
	@PutMapping(value = "/")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
		Customer updateCustomer = null;
		try {
			updateCustomer = customerService.saveCustomer(customer);
			log.info("Data is Updated");
			return ResponseEntity.of(Optional.of(updateCustomer));
		} catch (Exception e) {
			e.printStackTrace();
			log.info("INTERNAL SERVER ERROR");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// DELETE CUSTOMER BY ID REST API
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> deleteBookById(@PathVariable("id") int id) {
		try {
			customerRepository.deleteById(id);
			log.info("Customer with" + id + " is deleted");
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (NoSuchElementException nse) {
			nse.printStackTrace();
			log.info("No Data is present with that" + id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			e.printStackTrace();
			log.info("INTERNAL SERVER ERROR");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
