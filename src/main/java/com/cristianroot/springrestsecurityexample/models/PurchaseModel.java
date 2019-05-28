package com.cristianroot.springrestsecurityexample.models;

import com.cristianroot.springrestsecurityexample.entities.Purchase;

import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.Optional;

public class PurchaseModel {

	private Long id;

	private String orderNumber;

	private ClientModel clientModel;

	@Min(1)
	private int quantity;

	private VinylModel vinylModel;

	private LocalDate purchaseDate;

	public static PurchaseModel from(Purchase purchase) {
		PurchaseModel purchaseModel = new PurchaseModel();
		purchaseModel.setId(purchase.getId());
		purchaseModel.setOrderNumber(purchase.getOrderNumber());
		purchaseModel.setPurchaseDate(purchase.getPurchaseDate());
		purchaseModel.setQuantity(purchase.getQuantity());
		purchaseModel.setVinyl(VinylModel.from(purchase.getVinyl()));
		purchaseModel.setClient(ClientModel.from(purchase.getClient()));
		return purchaseModel;
	}

	public Optional<Long> getId() {
		return Optional.ofNullable(id);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public ClientModel getClient() {
		return clientModel;
	}

	public void setClient(ClientModel clientModel) {
		this.clientModel = clientModel;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public VinylModel getVinyl() {
		return vinylModel;
	}

	public void setVinyl(VinylModel vinylModel) {
		this.vinylModel = vinylModel;
	}
}
