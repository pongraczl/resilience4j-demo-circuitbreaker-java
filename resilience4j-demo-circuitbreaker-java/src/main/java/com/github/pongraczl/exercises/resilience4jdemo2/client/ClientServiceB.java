package com.github.pongraczl.exercises.resilience4jdemo2.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.github.pongraczl.exercises.resilience4jdemo2.GeneralService;
import com.github.pongraczl.exercises.resilience4jdemo2.fakeremote.FailingRemoteController;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class ClientServiceB implements GeneralService {

	@Value("${server.port}")
	private String serverPort;
	
	@Override
	@CircuitBreaker(name = "serviceB", fallbackMethod = "fallBackOnFail")
	public String getResult() {
		RestTemplate restTemplate = new RestTemplate();
		String remoteResult = restTemplate.getForObject(getRemoteServicePath(), String.class);
		String ownResult = GeneralService.super.getResult();
		return ownResult + ": " + remoteResult;
	}
	
	private String getRemoteServicePath() {
		return "http://localhost:" + serverPort + FailingRemoteController.PATH;
	}
	
	@SuppressWarnings("unused")
	private String fallBackOnFail(CallNotPermittedException e) {
		return "(Circuit breaker is open)";
	}
	
	@SuppressWarnings("unused")
	private String fallBackOnFail(Exception e) {
		return "(No valid response)";
	}
}
