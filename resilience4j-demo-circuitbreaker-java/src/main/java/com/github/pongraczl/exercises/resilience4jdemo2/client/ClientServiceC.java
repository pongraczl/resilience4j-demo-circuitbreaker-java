package com.github.pongraczl.exercises.resilience4jdemo2.client;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pongraczl.exercises.resilience4jdemo2.GeneralService;

@Service
public class ClientServiceC implements GeneralService {

	private static final int REPEAT = 20;
	
	@Autowired
	private ClientServiceB clientServiceB;
	
	@Override
	public String getResult() {
		String result = Stream.generate(clientServiceB::getResult)
				.limit(REPEAT)
				.collect(Collectors.joining("<br>"));
		return String.format("Originally waiting for %d responses. %s:<br>%s", 
				REPEAT, 
				GeneralService.super.getResult(), 
				result);
	}
}
