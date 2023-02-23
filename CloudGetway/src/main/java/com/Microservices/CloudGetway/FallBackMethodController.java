package com.Microservices.CloudGetway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackMethodController {

	// Whenever the Service is down the request is directed here that is
	// #FallBackController

	@GetMapping("/accountServiceFallBack")
	public String accountServiceFallBackMethod() {
		return "Account Service is taking longer than Expected." + " Please try again later";
	}

	@GetMapping("/customerServiceFallBack")
	public String customerServiceFallBackMethod() {
		return "Customer Service is taking longer than Expected." + " Please try again later";
	}
}
