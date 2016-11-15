package org.tallymed.ui.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;

public class ProductInventory {
	private IntegerProperty inventoryID;
	private IntegerProperty productID;
	private IntegerProperty stock;
	private ObjectProperty<LocalDate> dateOfPurchase;
	public IntegerProperty inventoryIDProperty() {
		return this.inventoryID;
	}
	
	public int getInventoryID() {
		return this.inventoryIDProperty().get();
	}
	
	public void setInventoryID(final int inventoryID) {
		this.inventoryIDProperty().set(inventoryID);
	}
	
	public IntegerProperty productIDProperty() {
		return this.productID;
	}
	
	public int getProductID() {
		return this.productIDProperty().get();
	}
	
	public void setProductID(final int productID) {
		this.productIDProperty().set(productID);
	}
	
	public IntegerProperty stockProperty() {
		return this.stock;
	}
	
	public int getStock() {
		return this.stockProperty().get();
	}
	
	public void setStock(final int stock) {
		this.stockProperty().set(stock);
	}
	
	public ObjectProperty<LocalDate> dateOfPurchaseProperty() {
		return this.dateOfPurchase;
	}
	
	public LocalDate getDateOfPurchase() {
		return this.dateOfPurchaseProperty().get();
	}
	
	public void setDateOfPurchase(final LocalDate dateOfPurchase) {
		this.dateOfPurchaseProperty().set(dateOfPurchase);
	}
	
}
