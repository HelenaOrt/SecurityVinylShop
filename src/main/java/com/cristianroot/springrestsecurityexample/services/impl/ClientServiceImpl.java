/******************************************************************************
 * Copyright (c) 2019. Cristian Gonzalez Morante                              *
 ******************************************************************************/

package com.cristianroot.springrestsecurityexample.services.impl;

import com.cristianroot.springrestsecurityexample.entities.Client;
import com.cristianroot.springrestsecurityexample.exceptions.DuplicatedEntityException;
import com.cristianroot.springrestsecurityexample.exceptions.EntityNotFoundException;
import com.cristianroot.springrestsecurityexample.exceptions.IdRequiredException;
import com.cristianroot.springrestsecurityexample.exceptions.IllegalOperationException;
import com.cristianroot.springrestsecurityexample.models.ClientModel;
import com.cristianroot.springrestsecurityexample.repositories.ClientRepository;
import com.cristianroot.springrestsecurityexample.repositories.PurchaseRepository;
import com.cristianroot.springrestsecurityexample.services.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

	private final ClientRepository clientRepository;
	private final PurchaseRepository purchaseRepository;

	public ClientServiceImpl(ClientRepository clientRepository, PurchaseRepository purchaseRepository) {
		this.clientRepository = clientRepository;
		this.purchaseRepository = purchaseRepository;
	}

	@Override
	public List<ClientModel> findAll() {
		return clientRepository.findAll().stream()
							   .map(ClientModel::from)
							   .collect(Collectors.toList());
	}

	@Override
	public ClientModel findOne(long id) throws EntityNotFoundException {
		return clientRepository.findById(id)
							   .map(ClientModel::from)
							   .orElseThrow(() -> new EntityNotFoundException(Client.class, id));
	}

	@Override
	public ClientModel save(ClientModel clientModel) throws DuplicatedEntityException {
		if (clientRepository.findByNameIgnoreCase(clientModel.getName()).isPresent())
			throw new DuplicatedEntityException();

		Client client = new Client();
		client.setName(clientModel.getName());

		return ClientModel.from(clientRepository.save(client));
	}

	@Override
	public ClientModel update(long id, ClientModel clientModel) throws EntityNotFoundException, DuplicatedEntityException, IdRequiredException, IllegalOperationException {
		long modelId = clientModel.getId().orElseThrow(IdRequiredException::new);

		if (id != modelId)
			throw new IllegalOperationException("IDs doesn't match");

		Client client = clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Client.class, id));
		Optional<Client> duplicatedClient = clientRepository.findByNameIgnoreCase(clientModel.getName());
		if (duplicatedClient.isPresent()) {
			if (duplicatedClient.get().getId() != client.getId()) {
				throw new DuplicatedEntityException();
			}
		}
		if (clientRepository.findByNameIgnoreCase(clientModel.getName()).isPresent())
			throw new DuplicatedEntityException();

		client.setName(clientModel.getName());

		return ClientModel.from(clientRepository.save(client));
	}

	@Override
	public void delete(long id) throws EntityNotFoundException {
		Client client = clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Client.class, id));
		clientRepository.delete(client);
	}

}
