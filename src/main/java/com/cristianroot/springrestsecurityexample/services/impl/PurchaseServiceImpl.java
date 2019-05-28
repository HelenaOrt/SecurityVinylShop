package com.cristianroot.springrestsecurityexample.services.impl;

import com.cristianroot.springrestsecurityexample.entities.Client;
import com.cristianroot.springrestsecurityexample.entities.Purchase;
import com.cristianroot.springrestsecurityexample.entities.Vinyl;
import com.cristianroot.springrestsecurityexample.exceptions.DuplicatedEntityException;
import com.cristianroot.springrestsecurityexample.exceptions.EntityNotFoundException;
import com.cristianroot.springrestsecurityexample.exceptions.IdRequiredException;
import com.cristianroot.springrestsecurityexample.exceptions.IllegalOperationException;
import com.cristianroot.springrestsecurityexample.models.PurchaseModel;
import com.cristianroot.springrestsecurityexample.repositories.ClientRepository;
import com.cristianroot.springrestsecurityexample.repositories.PurchaseRepository;
import com.cristianroot.springrestsecurityexample.repositories.VinylRepository;
import com.cristianroot.springrestsecurityexample.services.PurchaseService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PurchaseServiceImpl implements PurchaseService {

	private final VinylRepository vinylRepository;
	private final ClientRepository clientRepository;
	private final PurchaseRepository purchaseRepository;

	public PurchaseServiceImpl(VinylRepository vinylRepository,
							   ClientRepository clientRepository, PurchaseRepository purchaseRepository) {
		this.vinylRepository = vinylRepository;
		this.clientRepository = clientRepository;
		this.purchaseRepository = purchaseRepository;
	}

	@Override
	public List<PurchaseModel> findAll() {
		return purchaseRepository.findAll().stream()
								 .map(PurchaseModel::from).collect(Collectors.toList());
	}

	@Override
	public PurchaseModel findOne(long id) throws EntityNotFoundException {
		return purchaseRepository.findById(id).map(PurchaseModel::from).orElseThrow(() -> new EntityNotFoundException(Purchase.class, id));
	}

	@Override
	public PurchaseModel save(PurchaseModel purchaseModel) throws DuplicatedEntityException, IdRequiredException, EntityNotFoundException {
		if (purchaseRepository.findByOrderNumberIgnoreCase(purchaseModel.getOrderNumber()).isPresent())
			throw new DuplicatedEntityException();

		long clientId = purchaseModel.getClient().getId().orElseThrow(IdRequiredException::new);
		long vinylId = purchaseModel.getVinyl().getId().orElseThrow(IdRequiredException::new);

		Purchase purchase = new Purchase();
		purchase.setOrderNumber(purchaseModel.getOrderNumber());
		purchase.setClient(clientRepository.findById(clientId).orElseThrow(() -> new EntityNotFoundException(Client.class, clientId)));
		purchase.setQuantity(purchaseModel.getQuantity());
		purchase.setPurchaseDate(LocalDate.now());
		purchase.setVinyl(vinylRepository.findById(vinylId).orElseThrow(() -> new EntityNotFoundException(Vinyl.class, vinylId)));

		return PurchaseModel.from(purchaseRepository.save(purchase));
	}

	@Override
	public PurchaseModel update(long id, PurchaseModel purchaseModel) throws EntityNotFoundException, DuplicatedEntityException, IdRequiredException, IllegalOperationException {
		long purchaseId = purchaseModel.getId().orElseThrow(IdRequiredException::new);

		if (id != purchaseId)
			throw new IllegalOperationException("ID doesn't match");

		Purchase purchase = purchaseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Purchase.class, id));
		Optional<Purchase> duplicatedPurchase = purchaseRepository.findByOrderNumberIgnoreCase(purchaseModel.getOrderNumber());

		if (duplicatedPurchase.isPresent()) {
			if (duplicatedPurchase.get().getId() != purchase.getId())
				throw new DuplicatedEntityException();
		}

		long clientId = purchaseModel.getClient().getId().orElseThrow(IdRequiredException::new);
		long vinylId = purchaseModel.getVinyl().getId().orElseThrow(IdRequiredException::new);

		purchase.setOrderNumber(purchaseModel.getOrderNumber());
		purchase.setPurchaseDate(purchaseModel.getPurchaseDate());
		purchase.setQuantity(purchaseModel.getQuantity());
		purchase.setClient(clientRepository.findById(clientId).orElseThrow(() -> new EntityNotFoundException(Client.class, clientId)));
		purchase.setVinyl(vinylRepository.findById(vinylId).orElseThrow(() -> new EntityNotFoundException(Vinyl.class, vinylId)));

		return PurchaseModel.from(purchaseRepository.save(purchase));

	}

	@Override
	public void delete(long id) throws EntityNotFoundException {
		Purchase purchase = purchaseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Purchase.class, id));
		purchaseRepository.delete(purchase);

	}
}
