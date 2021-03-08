package com.github.pongraczl.exercises.resilience4jdemo2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	
	@GetMapping("/")
	public String home() {
		return "Hello Resilience4j CircuitBreaker Java Demo!";
	}
}
