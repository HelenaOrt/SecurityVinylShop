/******************************************************************************
 * Copyright (c) 2019. Cristian Gonzalez Morante                              *
 ******************************************************************************/

package com.cristianroot.springrestsecurityexample.entities;

import com.cristianroot.springrestsecurityexample.constants.VinylSize;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Vinyl {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@Column(unique = true)
	private String name;

	@NotNull
	private LocalDate publishDate;

	@NotNull
	@Enumerated(EnumType.STRING)
	private VinylSize size;

	@NotNull
	@Min(1)
	private double price;

	@ManyToOne
	private MusicGroup musicGroup;

	@OneToMany(mappedBy = "vinyl", cascade = CascadeType.REMOVE)
	private List<Purchase> purchaseList;

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

	public LocalDate getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(LocalDate publishDate) {
		this.publishDate = publishDate;
	}

	public VinylSize getSize() {
		return size;
	}

	public void setSize(VinylSize size) {
		this.size = size;
	}

	public MusicGroup getMusicGroup() {
		return musicGroup;
	}

	public void setMusicGroup(MusicGroup musicGroup) {
		this.musicGroup = musicGroup;
	}

	public List<Purchase> getPurchaseList() {
		return purchaseList;
	}

	public void setPurchaseList(List<Purchase> purchaseList) {
		this.purchaseList = purchaseList;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getVinylsSold() {
		return purchaseList.stream().mapToInt(Purchase::getQuantity).sum();
	}
}

