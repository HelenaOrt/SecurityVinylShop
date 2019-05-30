/******************************************************************************
 * Copyright (c) 2019. Cristian Gonzalez Morante                              *
 ******************************************************************************/

package com.cristianroot.springrestsecurityexample.repositories;

import com.cristianroot.springrestsecurityexample.entities.MusicGroup;
import com.cristianroot.springrestsecurityexample.models.MusicGroupModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<MusicGroup, Long> {

	Optional<MusicGroup> findByNameIgnoreCase(String name);

	@Query("select m from MusicGroup m join m.publishedVinylList v join v.purchaseList p group by m.id order by sum(p.quantity) desc")
	List<MusicGroupModel> findTop5();

}
