/******************************************************************************
 * Copyright (c) 2019. Cristian Gonzalez Morante                              *
 ******************************************************************************/

package com.cristianroot.springrestsecurityexample.repositories;

import com.cristianroot.springrestsecurityexample.entities.Vinyl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VinylRepository extends JpaRepository<Vinyl, Long> {

	Optional<Vinyl> findByNameIgnoreCase(String name);
}
