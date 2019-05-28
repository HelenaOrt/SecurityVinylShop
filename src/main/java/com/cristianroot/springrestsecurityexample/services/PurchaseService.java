package com.cristianroot.springrestsecurityexample.services;

import com.cristianroot.springrestsecurityexample.exceptions.DuplicatedEntityException;
import com.cristianroot.springrestsecurityexample.exceptions.EntityNotFoundException;
import com.cristianroot.springrestsecurityexample.exceptions.IdRequiredException;
import com.cristianroot.springrestsecurityexample.exceptions.IllegalOperationException;
import com.cristianroot.springrestsecurityexample.models.PurchaseModel;

import java.util.List;

public interface PurchaseService {

	List<PurchaseModel> findAll();

	PurchaseModel findOne(long id) throws EntityNotFoundException;

	PurchaseModel save(PurchaseModel purchaseModel) throws DuplicatedEntityException, IdRequiredException, EntityNotFoundException;

	PurchaseModel update(long id, PurchaseModel purchaseModel) throws EntityNotFoundException, DuplicatedEntityException, IdRequiredException, IllegalOperationException;

	void delete(long id) throws EntityNotFoundException;
}
