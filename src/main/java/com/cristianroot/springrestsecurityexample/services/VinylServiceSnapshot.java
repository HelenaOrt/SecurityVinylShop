package com.cristianroot.springrestsecurityexample.services;

import com.cristianroot.springrestsecurityexample.constants.VinylSize;
import com.cristianroot.springrestsecurityexample.exceptions.EntityNotFoundException;
import com.cristianroot.springrestsecurityexample.models.VinylModel;

import java.util.List;
import java.util.Map;

public interface VinylServiceSnapshot {

	int countVinyls() throws EntityNotFoundException;

	List<VinylModel> findTop5() throws EntityNotFoundException;

	public Map<VinylSize, Long> findAllBySize() throws EntityNotFoundException;

}
