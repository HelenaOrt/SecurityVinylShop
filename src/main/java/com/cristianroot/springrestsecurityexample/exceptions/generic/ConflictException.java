/******************************************************************************
 * Copyright (c) 2019. Cristian Gonzalez Morante                              *
 ******************************************************************************/

package com.cristianroot.springrestsecurityexample.exceptions.generic;

public class ConflictException extends Exception {

	protected ConflictException() {
	}

	protected ConflictException(String message) {
		super(message);
	}

}
