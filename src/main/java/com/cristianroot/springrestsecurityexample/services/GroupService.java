/******************************************************************************
 * Copyright (c) 2019. Cristian Gonzalez Morante                              *
 ******************************************************************************/

package com.cristianroot.springrestsecurityexample.services;

import com.cristianroot.springrestsecurityexample.exceptions.DuplicatedEntityException;
import com.cristianroot.springrestsecurityexample.exceptions.EntityNotFoundException;
import com.cristianroot.springrestsecurityexample.exceptions.IdRequiredException;
import com.cristianroot.springrestsecurityexample.exceptions.IllegalOperationException;
import com.cristianroot.springrestsecurityexample.models.MusicGroupModel;

import java.util.List;

public interface GroupService {

	List<MusicGroupModel> findAll();

	MusicGroupModel findOne(long id) throws EntityNotFoundException;

	MusicGroupModel save(MusicGroupModel musicGroupModel) throws DuplicatedEntityException, IdRequiredException, EntityNotFoundException;

	MusicGroupModel update(long id, MusicGroupModel musicGroupModel) throws EntityNotFoundException, DuplicatedEntityException, IdRequiredException, IllegalOperationException;

	void delete(long id) throws EntityNotFoundException;

}
