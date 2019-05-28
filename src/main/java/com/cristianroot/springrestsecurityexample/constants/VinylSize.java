/******************************************************************************
 * Copyright (c) 2019. Cristian Gonzalez Morante                              *
 ******************************************************************************/

package com.cristianroot.springrestsecurityexample.constants;

public enum VinylSize {

	MINI(17.5),
	SMALL(25),
	STANDARD(30);

	VinylSize(double i) {
		this.size = i;
	}

	private final double size;

	public double getSize() {
		return size;
	}
}
