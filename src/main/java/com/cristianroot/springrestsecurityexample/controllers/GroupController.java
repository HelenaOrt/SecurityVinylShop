/******************************************************************************
 * Copyright (c) 2019. Cristian Gonzalez Morante                              *
 ******************************************************************************/

package com.cristianroot.springrestsecurityexample.controllers;

import com.cristianroot.springrestsecurityexample.exceptions.DuplicatedEntityException;
import com.cristianroot.springrestsecurityexample.exceptions.EntityNotFoundException;
import com.cristianroot.springrestsecurityexample.exceptions.IdRequiredException;
import com.cristianroot.springrestsecurityexample.exceptions.IllegalOperationException;
import com.cristianroot.springrestsecurityexample.models.MusicGroupModel;
import com.cristianroot.springrestsecurityexample.models.snapshot.GroupSnapshot;
import com.cristianroot.springrestsecurityexample.services.GroupService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class GroupController {

	private final GroupService groupService;

	public GroupController(GroupService groupService) {
		this.groupService = groupService;
	}

	@GetMapping("/groups")
	public List<MusicGroupModel> findAll() {
		return groupService.findAll();
	}

	@GetMapping("/groups/{id}")
	public MusicGroupModel findOne(@PathVariable long id) throws EntityNotFoundException {
		return groupService.findOne(id);
	}

	@GetMapping("/groups/snapshot")
	public GroupSnapshot snapshot(GroupSnapshot groupSnapshot) {
		return groupService.groupSnapshot(groupSnapshot);
	}

	@PostMapping("/groups")
	public MusicGroupModel save(@Valid @RequestBody MusicGroupModel musicGroupModel)
			throws DuplicatedEntityException, IdRequiredException, EntityNotFoundException {
		return groupService.save(musicGroupModel);
	}

	@PutMapping("/groups/{id}")
	public MusicGroupModel update(@PathVariable long id, @RequestBody MusicGroupModel musicGroupModel)
			throws DuplicatedEntityException, IllegalOperationException, IdRequiredException, EntityNotFoundException {
		return groupService.update(id, musicGroupModel);
	}

	@DeleteMapping("/groups/{id}")
	public void delete(@PathVariable long id) throws EntityNotFoundException {
		groupService.delete(id);
	}

}
