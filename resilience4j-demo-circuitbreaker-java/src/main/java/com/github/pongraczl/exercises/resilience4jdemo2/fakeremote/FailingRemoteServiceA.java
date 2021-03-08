package com.github.pongraczl.exercises.resilience4jdemo2.fakeremote;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.github.pongraczl.exercises.resilience4jdemo2.GeneralService;

@Service
public class FailingRemoteServiceA implements GeneralService {

    private int ofThree = 0;//statefulness is not preferred, but good for solo-server example
	
	public String getRandomResult() {
		if (ThreadLocalRandom.current().nextInt(10) > 4) {
			return getResult();
		} else {
			throw new RuntimeException();
		}
	}
	
    public String twoOfThreeIsFailure() {
    	ofThree = (ofThree + 1) % 3;
    	if (ofThree % 3 == 0) {
    		return getResult();
    	} else {
    		throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "This is a remote exception");
    	}
    }

}
