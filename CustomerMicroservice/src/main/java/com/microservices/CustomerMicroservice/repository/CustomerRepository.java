package com.microservices.CustomerMicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.CustomerMicroservice.entity.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	Customer findByEmailAndPassword(String email, String password);

	Customer fetchCustomerByEmailId(String email);

	Customer CustomerById(int customerId);

}
