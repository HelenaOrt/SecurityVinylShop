package com.cristianroot.springrestsecurityexample.services.impl;

import com.cristianroot.springrestsecurityexample.constants.VinylSize;
import com.cristianroot.springrestsecurityexample.entities.Vinyl;
import com.cristianroot.springrestsecurityexample.exceptions.EntityNotFoundException;
import com.cristianroot.springrestsecurityexample.models.VinylModel;
import com.cristianroot.springrestsecurityexample.repositories.VinylRepository;
import com.cristianroot.springrestsecurityexample.services.VinylServiceSnapshot;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class VinylServiceSnapshotImpl implements VinylServiceSnapshot {

	private final VinylRepository vinylRepository;

	public VinylServiceSnapshotImpl(VinylRepository vinylRepository) {
		this.vinylRepository = vinylRepository;
	}

	@Override
	public int countVinyls() throws EntityNotFoundException {
		if (vinylRepository.findAll().isEmpty()) {
			throw new EntityNotFoundException(Vinyl.class);
		} else {
			return vinylRepository.findAll().size();
		}
	}

	@Override
	public List<VinylModel> findTop5() throws EntityNotFoundException {
		if (vinylRepository.findAll().isEmpty()) {
			throw new EntityNotFoundException(Vinyl.class);
		} else {
			return vinylRepository.findAll().stream()
								  .sorted((p1, p2) -> Integer.compare(p2.getVinylsSold(), p1.getVinylsSold()))
								  .map(VinylModel::from).collect(Collectors.toList());
		}
	}

	@Override
	public Map<VinylSize, Long> findAllBySize() throws EntityNotFoundException {
		if (vinylRepository.findAll().isEmpty()) {
			throw new EntityNotFoundException(Vinyl.class);
		} else {
			List<VinylModel> auxList = vinylRepository.findAll().stream().map(VinylModel::from).collect(Collectors.toList());
			Map<VinylSize, Long> vinylsBySizeMap = auxList
					.stream().collect(Collectors.groupingBy(VinylModel::getSize, Collectors.counting()));
			return vinylsBySizeMap;
		}
	}

}
