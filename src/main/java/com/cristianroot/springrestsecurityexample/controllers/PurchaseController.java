package com.cristianroot.springrestsecurityexample.controllers;

import com.cristianroot.springrestsecurityexample.exceptions.DuplicatedEntityException;
import com.cristianroot.springrestsecurityexample.exceptions.EntityNotFoundException;
import com.cristianroot.springrestsecurityexample.exceptions.IdRequiredException;
import com.cristianroot.springrestsecurityexample.exceptions.IllegalOperationException;
import com.cristianroot.springrestsecurityexample.models.PurchaseModel;
import com.cristianroot.springrestsecurityexample.services.PurchaseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PurchaseController {

	private final PurchaseService purchaseService;

	public PurchaseController(PurchaseService purchaseService) {
		this.purchaseService = purchaseService;
	}

	@GetMapping("/purchases")
	public List<PurchaseModel> findAll() {
		return purchaseService.findAll();
	}

	@GetMapping("/purchases/{id}")
	public PurchaseModel findOne(@PathVariable long id) throws EntityNotFoundException {
		return purchaseService.findOne(id);
	}

	@PostMapping("/purchases")
	@ResponseStatus(HttpStatus.CREATED)
	public PurchaseModel save(@Valid @RequestBody PurchaseModel purchaseModel) throws DuplicatedEntityException, IdRequiredException, EntityNotFoundException {
		return purchaseService.save(purchaseModel);
	}

	@PutMapping("/purchases/{id}")
	public PurchaseModel update(@PathVariable long id, @RequestBody PurchaseModel purchaseModel) throws DuplicatedEntityException, IllegalOperationException, IdRequiredException, EntityNotFoundException {
		return purchaseService.update(id, purchaseModel);
	}

	@DeleteMapping("/purchases/{id}")
	public void delete(@PathVariable long id) throws EntityNotFoundException {
		purchaseService.delete(id);
	}
}
