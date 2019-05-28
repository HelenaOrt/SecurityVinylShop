/******************************************************************************
 * Copyright (c) 2019. Cristian Gonzalez Morante                              *
 ******************************************************************************/

package com.cristianroot.springrestsecurityexample.models;

import com.cristianroot.springrestsecurityexample.constants.VinylSize;
import com.cristianroot.springrestsecurityexample.entities.Vinyl;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Optional;

public class VinylModel {

	private Long id;

	private MusicGroupModel group;

	@NotNull
	private Double price;

	@NotNull
	@Size(min = 2)
	private String name;

	@NotNull
	private VinylSize size;

	public static VinylModel from(Vinyl vinyl) {
		VinylModel vinylModel = new VinylModel();
		vinylModel.setId(vinyl.getId());
		vinylModel.setName(vinyl.getName());
		vinylModel.setPrice(vinyl.getPrice());
		vinylModel.setGroup(MusicGroupModel.from(vinyl.getMusicGroup()));
		vinylModel.setSize(vinyl.getSize());
		return vinylModel;
	}

	public VinylSize getSize() {
		return size;
	}

	public VinylModel setSize(VinylSize size) {
		this.size = size;
		return this;
	}

	public Optional<Long> getId() {
		return Optional.ofNullable(id);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MusicGroupModel getGroup() {
		return group;
	}

	public void setGroup(MusicGroupModel group) {
		this.group = group;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
