/******************************************************************************
 * Copyright (c) 2019. Cristian Gonzalez Morante                              *
 ******************************************************************************/

package com.cristianroot.springrestsecurityexample.services.impl;

import com.cristianroot.springrestsecurityexample.entities.MusicGroup;
import com.cristianroot.springrestsecurityexample.exceptions.DuplicatedEntityException;
import com.cristianroot.springrestsecurityexample.exceptions.EntityNotFoundException;
import com.cristianroot.springrestsecurityexample.exceptions.IdRequiredException;
import com.cristianroot.springrestsecurityexample.exceptions.IllegalOperationException;
import com.cristianroot.springrestsecurityexample.models.MusicGroupModel;
import com.cristianroot.springrestsecurityexample.models.snapshot.GroupSnapshot;
import com.cristianroot.springrestsecurityexample.repositories.GroupRepository;
import com.cristianroot.springrestsecurityexample.services.GroupService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {

	private final GroupRepository groupRepository;

	public GroupServiceImpl(GroupRepository groupRepository) {
		this.groupRepository = groupRepository;
	}

	@Override
	public List<MusicGroupModel> findAll() {
		return groupRepository.findAll().stream()
							  .map(MusicGroupModel::from)
							  .collect(Collectors.toList());
	}

	@Override
	public MusicGroupModel findOne(long id) throws EntityNotFoundException {
		return groupRepository.findById(id)
							  .map(MusicGroupModel::from)
							  .orElseThrow(() -> new EntityNotFoundException(MusicGroup.class, id));
	}

	@Override
	public MusicGroupModel save(MusicGroupModel musicGroupModel) throws DuplicatedEntityException, IdRequiredException, EntityNotFoundException {
		if (groupRepository.findByNameIgnoreCase(musicGroupModel.getName()).isPresent())
			throw new DuplicatedEntityException();

		MusicGroup musicGroup = new MusicGroup();
		musicGroup.setName(musicGroupModel.getName());
		musicGroup.setMembers(musicGroupModel.getMembers());

		return MusicGroupModel.from(groupRepository.save(musicGroup));
	}

	@Override
	public MusicGroupModel update(long id, MusicGroupModel musicGroupModel) throws EntityNotFoundException, DuplicatedEntityException, IdRequiredException, IllegalOperationException {
		long modelId = musicGroupModel.getId().orElseThrow(IdRequiredException::new);

		if (id != modelId)
			throw new IllegalOperationException("IDs doesn't match");

		MusicGroup musicGroup = groupRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(MusicGroup.class, id));
		Optional<MusicGroup> duplicatedMusicGroup = groupRepository.findByNameIgnoreCase(musicGroupModel.getName());
		if (duplicatedMusicGroup.isPresent()) {
			if (duplicatedMusicGroup.get().getId() != musicGroup.getId())
				throw new DuplicatedEntityException();

		}

		long vinylId = musicGroupModel.getId().orElseThrow(IdRequiredException::new);

		musicGroup.setName(musicGroupModel.getName());
		musicGroup.setMembers(musicGroupModel.getMembers());

		return MusicGroupModel.from(groupRepository.save(musicGroup));
	}

	@Override
	public void delete(long id) throws EntityNotFoundException {
		MusicGroup musicGroup = groupRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(MusicGroup.class, id));
		groupRepository.delete(musicGroup);
	}

	@Override
	public GroupSnapshot groupSnapshot(GroupSnapshot groupSnapshot) {
		groupSnapshot.setTop5groups(groupRepository.findTop5());
		groupSnapshot.setGroupNumber(groupRepository.findAll().size());
		return groupSnapshot;
	}
}


