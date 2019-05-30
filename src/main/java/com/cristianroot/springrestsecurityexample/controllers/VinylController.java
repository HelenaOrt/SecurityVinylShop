/******************************************************************************
 * Copyright (c) 2019. Cristian Gonzalez Morante                              *
 ******************************************************************************/

package com.cristianroot.springrestsecurityexample.controllers;

import com.cristianroot.springrestsecurityexample.exceptions.DuplicatedEntityException;
import com.cristianroot.springrestsecurityexample.exceptions.EntityNotFoundException;
import com.cristianroot.springrestsecurityexample.exceptions.IdRequiredException;
import com.cristianroot.springrestsecurityexample.exceptions.IllegalOperationException;
import com.cristianroot.springrestsecurityexample.models.VinylModel;
import com.cristianroot.springrestsecurityexample.models.snapshot.VinylSnapshot;
import com.cristianroot.springrestsecurityexample.services.VinylService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class VinylController {

	private final VinylService vinylService;

	public VinylController(VinylService vinylService) {
		this.vinylService = vinylService;
	}

	@GetMapping("/vinyls")
	public List<VinylModel> findAll() {
		return vinylService.findAll();
	}

	@GetMapping("/vinyls/{id}")
	public VinylModel findOne(@PathVariable long id) throws EntityNotFoundException {
		return vinylService.findOne(id);
	}

	@GetMapping("/vinyls/snapshot")
	public VinylSnapshot snapshot(VinylSnapshot vinylSnapshot) {
		return vinylService.vinylSnapshot(vinylSnapshot);
	}

	@PostMapping("/vinyls")
	@ResponseStatus(HttpStatus.CREATED)
	public VinylModel save(@Valid @RequestBody VinylModel vinylModel) throws DuplicatedEntityException, IdRequiredException, EntityNotFoundException {
		return vinylService.save(vinylModel);
	}

	@PutMapping("/vinyls/{id}")
	public VinylModel update(@PathVariable long id, @RequestBody VinylModel vinylModel) throws DuplicatedEntityException, IllegalOperationException, IdRequiredException, EntityNotFoundException {
		return vinylService.update(id, vinylModel);
	}

	@DeleteMapping("/vinyls/{id}")
	public void delete(@PathVariable long id) throws EntityNotFoundException {
		vinylService.delete(id);
	}

}
