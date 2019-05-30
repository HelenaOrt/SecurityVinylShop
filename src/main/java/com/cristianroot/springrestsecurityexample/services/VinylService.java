/******************************************************************************
 * Copyright (c) 2019. Cristian Gonzalez Morante                              *
 ******************************************************************************/

package com.cristianroot.springrestsecurityexample.services;

import com.cristianroot.springrestsecurityexample.exceptions.DuplicatedEntityException;
import com.cristianroot.springrestsecurityexample.exceptions.EntityNotFoundException;
import com.cristianroot.springrestsecurityexample.exceptions.IdRequiredException;
import com.cristianroot.springrestsecurityexample.exceptions.IllegalOperationException;
import com.cristianroot.springrestsecurityexample.models.VinylModel;
import com.cristianroot.springrestsecurityexample.models.snapshot.VinylSnapshot;

import java.util.List;

public interface VinylService {

	List<VinylModel> findAll();

	VinylModel findOne(long id) throws EntityNotFoundException;

	VinylModel save(VinylModel vinylModel) throws DuplicatedEntityException, IdRequiredException, EntityNotFoundException;

	VinylModel update(long id, VinylModel vinylModel) throws EntityNotFoundException, DuplicatedEntityException, IdRequiredException, IllegalOperationException;

	void delete(long id) throws EntityNotFoundException;

	VinylSnapshot vinylSnapshot(VinylSnapshot vinylSnapshot);

}
