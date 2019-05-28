/******************************************************************************
 * Copyright (c) 2019. Cristian Gonzalez Morante                              *
 ******************************************************************************/

package com.cristianroot.springrestsecurityexample.exceptions.generic;

public class NotFoundException extends Exception {

	protected NotFoundException() {
	}

	protected NotFoundException(String message) {
		super(message);
	}

}
