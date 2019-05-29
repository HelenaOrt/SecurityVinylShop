/******************************************************************************
 * Copyright (c) 2019. Cristian Gonzalez Morante                              *
 ******************************************************************************/

package com.cristianroot.springrestsecurityexample.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class MusicGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@Column(unique = true)
	private String name;

	@NotNull
	@Min(1)
	private int members;

	@OneToMany(mappedBy = "musicGroup", cascade = {CascadeType.REMOVE, CascadeType.REFRESH})
	private List<Vinyl> publishedVinylList;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMembers() {
		return members;
	}

	public void setMembers(int members) {
		this.members = members;
	}

	public List<Vinyl> getPublishedVinylList() {
		return publishedVinylList;
	}

	public void setPublishedVinylList(List<Vinyl> publishedVinylList) {
		this.publishedVinylList = publishedVinylList;
	}

	public void addVinyl(Vinyl vinyl) {
		publishedVinylList.add(vinyl);
	}

}
