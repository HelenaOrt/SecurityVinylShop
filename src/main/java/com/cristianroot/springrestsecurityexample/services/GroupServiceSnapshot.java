package com.cristianroot.springrestsecurityexample.services;

import com.cristianroot.springrestsecurityexample.exceptions.EntityNotFoundException;
import com.cristianroot.springrestsecurityexample.models.MusicGroupModel;

import java.util.List;

public interface GroupServiceSnapshot {

	int countGrups() throws EntityNotFoundException;

	List<MusicGroupModel> findTop5() throws EntityNotFoundException;

}
