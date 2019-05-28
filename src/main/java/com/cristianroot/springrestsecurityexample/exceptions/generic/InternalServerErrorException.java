/******************************************************************************
 * Copyright (c) 2019. Cristian Gonzalez Morante                              *
 ******************************************************************************/

package com.cristianroot.springrestsecurityexample.exceptions.generic;

public class InternalServerErrorException extends Exception {

	protected InternalServerErrorException() {
	}

	protected InternalServerErrorException(String message) {
		super(message);
	}

}
