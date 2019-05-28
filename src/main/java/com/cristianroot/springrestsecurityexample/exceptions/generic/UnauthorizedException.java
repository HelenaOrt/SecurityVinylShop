/******************************************************************************
 * Copyright (c) 2019. Cristian Gonzalez Morante                              *
 ******************************************************************************/

package com.cristianroot.springrestsecurityexample.exceptions.generic;

public class UnauthorizedException extends Exception {

	protected UnauthorizedException() {
	}

	protected UnauthorizedException(String message) {
		super(message);
	}

}
