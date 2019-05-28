/******************************************************************************
 * Copyright (c) 2019. Cristian Gonzalez Morante                              *
 ******************************************************************************/

package com.cristianroot.springrestsecurityexample.controllers;

import com.cristianroot.springrestsecurityexample.exceptions.DuplicatedEntityException;
import com.cristianroot.springrestsecurityexample.exceptions.EntityNotFoundException;
import com.cristianroot.springrestsecurityexample.exceptions.IdRequiredException;
import com.cristianroot.springrestsecurityexample.exceptions.IllegalOperationException;
import com.cristianroot.springrestsecurityexample.models.ClientModel;
import com.cristianroot.springrestsecurityexample.services.ClientService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ClientController {

	private final ClientService clientService;

	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}

	@GetMapping("/clients")
	public List<ClientModel> findAll() {
		return clientService.findAll();
	}

	@GetMapping("/clients/{id}")
	public ClientModel findOne(@PathVariable long id) throws EntityNotFoundException {
		return clientService.findOne(id);
	}

	@PostMapping("/clients")
	public ClientModel save(@Valid @RequestBody ClientModel clientModel) throws DuplicatedEntityException {
		return clientService.save(clientModel);
	}

	@PutMapping("/clients/{id}")
	public ClientModel update(@PathVariable long id, @RequestBody ClientModel clientModel) throws DuplicatedEntityException, IllegalOperationException, IdRequiredException, EntityNotFoundException {
		return clientService.update(id, clientModel);
	}

	@DeleteMapping("/clients/{id}")
	public void delete(@PathVariable long id) throws EntityNotFoundException {
		clientService.delete(id);
	}

}
