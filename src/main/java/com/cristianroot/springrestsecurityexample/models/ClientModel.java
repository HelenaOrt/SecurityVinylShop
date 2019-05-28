/******************************************************************************
 * Copyright (c) 2019. Cristian Gonzalez Morante                              *
 ******************************************************************************/

package com.cristianroot.springrestsecurityexample.models;

import com.cristianroot.springrestsecurityexample.entities.Client;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public class ClientModel {

	private Long id;

	@NotNull
	private String name;

	public static ClientModel from(Client client) {
		ClientModel clientModel = new ClientModel();
		clientModel.setId(client.getId());
		clientModel.setName(client.getName());
		return clientModel;
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
}
