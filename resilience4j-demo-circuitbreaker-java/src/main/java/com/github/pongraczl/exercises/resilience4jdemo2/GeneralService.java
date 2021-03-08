package com.github.pongraczl.exercises.resilience4jdemo2;

public interface GeneralService {

	public default String getResult() {
		return String.format("Result of %s", this.getClass().getSimpleName());
	}
}
