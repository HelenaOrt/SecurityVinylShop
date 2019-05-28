/******************************************************************************
 * Copyright (c) 2019. Cristian Gonzalez Morante                              *
 ******************************************************************************/

package com.cristianroot.springrestsecurityexample.models;

import com.cristianroot.springrestsecurityexample.entities.MusicGroup;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public class MusicGroupModel {

	private Long id;

	@NotNull
	private String name;

	private List<VinylModel> publishedVinylList;

	@NotNull
	private Integer members;

	public static MusicGroupModel from(MusicGroup musicGroup) {
		MusicGroupModel musicGroupModel = new MusicGroupModel();
		musicGroupModel.setId(musicGroup.getId());
		musicGroupModel.setName(musicGroup.getName());
		musicGroupModel.setMembers(musicGroup.getMembers());
		return musicGroupModel;
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

	public Integer getMembers() {
		return members;
	}

	public void setMembers(Integer members) {
		this.members = members;
	}

}
