/******************************************************************************
 * Copyright (c) 2019. Cristian Gonzalez Morante                              *
 ******************************************************************************/

package com.cristianroot.springrestsecurityexample.exceptions;

import com.cristianroot.springrestsecurityexample.exceptions.generic.ConflictException;

public class DuplicatedEntityException extends ConflictException {

	public DuplicatedEntityException() {
		super("Duplicated entity");
	}
}
