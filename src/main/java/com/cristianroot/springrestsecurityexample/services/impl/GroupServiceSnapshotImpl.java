package com.cristianroot.springrestsecurityexample.services.impl;

import com.cristianroot.springrestsecurityexample.entities.MusicGroup;
import com.cristianroot.springrestsecurityexample.entities.Vinyl;
import com.cristianroot.springrestsecurityexample.exceptions.EntityNotFoundException;
import com.cristianroot.springrestsecurityexample.models.MusicGroupModel;
import com.cristianroot.springrestsecurityexample.repositories.GroupRepository;
import com.cristianroot.springrestsecurityexample.repositories.VinylRepository;
import com.cristianroot.springrestsecurityexample.services.GroupServiceSnapshot;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupServiceSnapshotImpl implements GroupServiceSnapshot {

	private final GroupRepository groupRepository;
	private final VinylRepository vinylRepository;

	public GroupServiceSnapshotImpl(GroupRepository groupRepository, VinylRepository vinylRepository) {
		this.groupRepository = groupRepository;
		this.vinylRepository = vinylRepository;
	}

	@Override
	public int countGrups() throws EntityNotFoundException {
		if (groupRepository.findAll().isEmpty()) {
			throw new EntityNotFoundException(MusicGroup.class);
		} else {
			return groupRepository.findAll().size();

		}
	}

	@Override
	public List<MusicGroupModel> findTop5() throws EntityNotFoundException {
		if (vinylRepository.findAll().isEmpty()) {
			throw new EntityNotFoundException(Vinyl.class);
		} else {

			return vinylRepository.findAll().stream()
								  .sorted((p1, p2) -> Integer.compare(p2.getVinylsSold(), p1.getVinylsSold()))
								  .map(Vinyl::getMusicGroup).map(MusicGroupModel::from).collect(Collectors.toList());
		}
	}
}
