package com.github.pongraczl.exercises.resilience4jdemo2.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FailingResponseClientController {

	public static final String PATH_MANY = "/ManyFailing2in3ResponseClientController";
	public static final String PATH_ONE = "/Failing2in3ResponseClientController";

	@Autowired
	private ClientServiceB clientServiceB;
	
	@Autowired
	private ClientServiceC clientServiceC;
	

	@GetMapping(PATH_MANY)
	public String get() {
		return clientServiceC.getResult();
	}

	@GetMapping(PATH_ONE)
	public String getOne() {
		return clientServiceB.getResult();
	}

}
