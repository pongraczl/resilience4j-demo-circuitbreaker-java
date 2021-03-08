package com.github.pongraczl.exercises.resilience4jdemo2.fakeremote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class FailingRemoteController {

	public static final String PATH = "/Failing2in3RemoteController";

	@Autowired
	private FailingRemoteServiceA randomFailingRemoteServiceA;

	@GetMapping(PATH)
	public String getResult() {
		try {
			return randomFailingRemoteServiceA.twoOfThreeIsFailure();
		} catch (RuntimeException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "FAILED");
		}
	}
}
