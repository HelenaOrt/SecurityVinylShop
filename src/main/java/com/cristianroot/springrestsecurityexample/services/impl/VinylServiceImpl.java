/******************************************************************************
 * Copyright (c) 2019. Cristian Gonzalez Morante                              *
 ******************************************************************************/

package com.cristianroot.springrestsecurityexample.services.impl;

import com.cristianroot.springrestsecurityexample.constants.VinylSize;
import com.cristianroot.springrestsecurityexample.entities.MusicGroup;
import com.cristianroot.springrestsecurityexample.entities.Vinyl;
import com.cristianroot.springrestsecurityexample.exceptions.DuplicatedEntityException;
import com.cristianroot.springrestsecurityexample.exceptions.EntityNotFoundException;
import com.cristianroot.springrestsecurityexample.exceptions.IdRequiredException;
import com.cristianroot.springrestsecurityexample.exceptions.IllegalOperationException;
import com.cristianroot.springrestsecurityexample.models.VinylModel;
import com.cristianroot.springrestsecurityexample.models.snapshot.VinylSnapshot;
import com.cristianroot.springrestsecurityexample.repositories.GroupRepository;
import com.cristianroot.springrestsecurityexample.repositories.VinylRepository;
import com.cristianroot.springrestsecurityexample.services.VinylService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VinylServiceImpl implements VinylService {


	private final VinylRepository vinylRepository;
	private final GroupRepository groupRepository;

	public VinylServiceImpl(VinylRepository vinylRepository, GroupRepository groupRepository) {
		this.vinylRepository = vinylRepository;
		this.groupRepository = groupRepository;
	}

	@Override
	public List<VinylModel> findAll() {
		return vinylRepository.findAll().stream()
							  .map(VinylModel::from)
							  .collect(Collectors.toList());
	}

	@Override
	public VinylModel findOne(long id) throws EntityNotFoundException {
		return vinylRepository.findById(id).map(VinylModel::from).orElseThrow(() -> new EntityNotFoundException(Vinyl.class, id));
	}

	@Override
	public VinylModel save(VinylModel vinylModel) throws DuplicatedEntityException, IdRequiredException, EntityNotFoundException {
		vinylRepository.findByNameIgnoreCase(vinylModel.getName()).orElseThrow(DuplicatedEntityException::new);

		long groupId = vinylModel.getGroup().getId().orElseThrow(IdRequiredException::new);
		Vinyl vinyl = new Vinyl();
		vinyl.setName(vinylModel.getName());
		vinyl.setPrice(vinylModel.getPrice());
		vinyl.setVinylSize(vinylModel.getVinylSize());
		vinyl.setPublishDate(LocalDate.now());
		vinyl.setMusicGroup(groupRepository.findById(groupId)
										   .orElseThrow(() -> new EntityNotFoundException(MusicGroup.class, groupId)));

		return VinylModel.from(vinylRepository.save(vinyl));
	}

	@Override
	public VinylModel update(long id, VinylModel vinylModel) throws EntityNotFoundException, DuplicatedEntityException, IdRequiredException, IllegalOperationException {
		long modelId = vinylModel.getId().orElseThrow(IdRequiredException::new);

		if (id != modelId)
			throw new IllegalOperationException("IDs doesn't match");

		Vinyl vinyl = vinylRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Vinyl.class, id));
		Optional<Vinyl> duplicatedVinyl = vinylRepository.findByNameIgnoreCase(vinylModel.getName());
		if (duplicatedVinyl.isPresent()) {
			if (duplicatedVinyl.get().getId() != vinyl.getId())
				throw new DuplicatedEntityException();
		}

		//otra forma de hacerlo
		/*vinylRepository.findByNameIgnoreCase(vinylModel.getName())
					   .map(Vinyl::getId)
					   .filter(duplicatedVinylId ->duplicatedVinylId == vinyl.getId())
					   .orElseThrow(DuplicatedEntityException::new);*/

		vinylRepository.findByNameIgnoreCase(vinylModel.getName()).orElseThrow(DuplicatedEntityException::new);

		long groupId = vinylModel.getGroup().getId().orElseThrow(IdRequiredException::new);

		vinyl.setName(vinylModel.getName());
		vinyl.setPrice(vinylModel.getPrice());
		vinyl.setVinylSize(vinylModel.getVinylSize());
		vinyl.setPublishDate(LocalDate.now());
		vinyl.setMusicGroup(groupRepository.findById(groupId)
										   .orElseThrow(() -> new EntityNotFoundException(MusicGroup.class, groupId)));

		return VinylModel.from(vinylRepository.save(vinyl));
	}

	@Override
	public void delete(long id) throws EntityNotFoundException {
		Vinyl vinyl = vinylRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Vinyl.class, id));
		vinylRepository.delete(vinyl);
	}

	@Override
	public VinylSnapshot vinylSnapshot(VinylSnapshot vinylSnapshot) {
		List<VinylModel> auxList = vinylRepository.findAll().stream().map(VinylModel::from).collect(Collectors.toList());
		Map<VinylSize, Long> vinylsBySizeMap = auxList
				.stream().collect(Collectors.groupingBy(VinylModel::getVinylSize, Collectors.counting()));

		vinylSnapshot.setTop5vinyls(vinylRepository.findTop5());
		vinylSnapshot.setVinylSizeMap(vinylsBySizeMap);
		vinylSnapshot.setVinylNumber(vinylRepository.findAll().size());
		return vinylSnapshot;
	}


}
