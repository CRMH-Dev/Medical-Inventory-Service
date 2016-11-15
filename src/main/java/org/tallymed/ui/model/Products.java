package org.tallymed.ui.model;

import java.time.LocalDate;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

public class Products {
	private StringProperty batchID;
	private IntegerProperty companyID;
	private ObjectProperty<LocalDate> expiryDate;
	private ObjectProperty<LocalDate> mfgDate;
	private IntegerProperty productID;
	private StringProperty productName;
	private DoubleProperty purchasePrice;
	private DoubleProperty sellingPrice;
	private IntegerProperty uomID;
	
	public StringProperty batchIDProperty() {
		return this.batchID;
	}
	
	public String getBatchID() {
		return this.batchIDProperty().get();
	}
	
	public void setBatchID(final String batchID) {
		this.batchIDProperty().set(batchID);
	}
	
	public IntegerProperty companyIDProperty() {
		return this.companyID;
	}
	
	public int getCompanyID() {
		return this.companyIDProperty().get();
	}
	
	public void setCompanyID(final int companyID) {
		this.companyIDProperty().set(companyID);
	}
	
	public ObjectProperty<LocalDate> expiryDateProperty() {
		return this.expiryDate;
	}
	
	public LocalDate getExpiryDate() {
		return this.expiryDateProperty().get();
	}
	
	public void setExpiryDate(final LocalDate expiryDate) {
		this.expiryDateProperty().set(expiryDate);
	}
	
	public ObjectProperty<LocalDate> mfgDateProperty() {
		return this.mfgDate;
	}
	
	public LocalDate getMfgDate() {
		return this.mfgDateProperty().get();
	}
	
	public void setMfgDate(final LocalDate mfgDate) {
		this.mfgDateProperty().set(mfgDate);
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
	
	public StringProperty productNameProperty() {
		return this.productName;
	}
	
	public String getProductName() {
		return this.productNameProperty().get();
	}
	
	public void setProductName(final String productName) {
		this.productNameProperty().set(productName);
	}
	
	public DoubleProperty purchasePriceProperty() {
		return this.purchasePrice;
	}
	
	public double getPurchasePrice() {
		return this.purchasePriceProperty().get();
	}
	
	public void setPurchasePrice(final double purchasePrice) {
		this.purchasePriceProperty().set(purchasePrice);
	}
	
	public DoubleProperty sellingPriceProperty() {
		return this.sellingPrice;
	}
	
	public double getSellingPrice() {
		return this.sellingPriceProperty().get();
	}
	
	public void setSellingPrice(final double sellingPrice) {
		this.sellingPriceProperty().set(sellingPrice);
	}
	
	public IntegerProperty uomIDProperty() {
		return this.uomID;
	}
	
	public int getUomID() {
		return this.uomIDProperty().get();
	}
	
	public void setUomID(final int uomID) {
		this.uomIDProperty().set(uomID);
	}
	
	
}
