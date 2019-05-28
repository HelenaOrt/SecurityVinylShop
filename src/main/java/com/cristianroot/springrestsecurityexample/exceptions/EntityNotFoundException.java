/******************************************************************************
 * Copyright (c) 2019. Cristian Gonzalez Morante                              *
 ******************************************************************************/

package com.cristianroot.springrestsecurityexample.exceptions;

import com.cristianroot.springrestsecurityexample.exceptions.generic.NotFoundException;

public class EntityNotFoundException extends NotFoundException {

	public EntityNotFoundException(Class clazz) {
		super(clazz.getSimpleName() + " not found ");
	}

	public EntityNotFoundException(Class clazz, long id) {
		super(clazz.getSimpleName() + " not found with id " + id);
	}

}
