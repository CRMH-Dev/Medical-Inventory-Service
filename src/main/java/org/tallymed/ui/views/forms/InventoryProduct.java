package org.tallymed.ui.views.forms;

import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class InventoryProduct extends RecursiveTreeObject<InventoryProduct> {

	private StringProperty dealer;

	private StringProperty batchId;
	
	private StringProperty productName;
	
	private StringProperty productComposition;
	
	private StringProperty mfgCompanyName;
	
	private StringProperty mfgShortName;
	
	private StringProperty unitType;
	
	private StringProperty mrp;
	
	private StringProperty purchasePrice;
	
	private StringProperty quantity;
	
	private StringProperty mfgDate;
	
	private StringProperty expDate;
	
	private StringProperty unitQuantity;

	public InventoryProduct(String dealer, String batchId, String productName,
			String productComposition, String mfgCompanyName, String mfgShortName,
			String unitType, String mrp, String purchasePrice, String quantity,
			String mfgDate, String expDate, String unitQuantity) {
		this.dealer = new SimpleStringProperty(dealer);
		this.batchId = new SimpleStringProperty(batchId);
		this.productName = new SimpleStringProperty(productName);
		this.productComposition = new SimpleStringProperty(productComposition);
		this.mfgCompanyName = new SimpleStringProperty(mfgCompanyName);
		this.mfgShortName = new SimpleStringProperty(mfgShortName);
		this.unitType = new SimpleStringProperty(unitType);
		this.mrp = new SimpleStringProperty(mrp);
		this.purchasePrice = new SimpleStringProperty(purchasePrice);
		this.quantity = new SimpleStringProperty(quantity);
		this.mfgDate = new SimpleStringProperty(mfgDate);
		this.expDate = new SimpleStringProperty(expDate);
		this.unitQuantity = new SimpleStringProperty(unitQuantity);
	}

	public StringProperty getDealer() {
		return dealer;
	}

	public void setDealer(StringProperty dealer) {
		this.dealer = dealer;
	}

	public StringProperty getBatchId() {
		return batchId;
	}

	public void setBatchId(StringProperty batchId) {
		this.batchId = batchId;
	}

	public StringProperty getProductName() {
		return productName;
	}

	public void setProductName(StringProperty productName) {
		this.productName = productName;
	}

	public StringProperty getProductComposition() {
		return productComposition;
	}

	public void setProductComposition(StringProperty productComposition) {
		this.productComposition = productComposition;
	}

	public StringProperty getMfgCompanyName() {
		return mfgCompanyName;
	}

	public void setMfgCompanyName(StringProperty mfgCompanyName) {
		this.mfgCompanyName = mfgCompanyName;
	}

	public StringProperty getMfgShortName() {
		return mfgShortName;
	}

	public void setMfgShortName(StringProperty mfgShortName) {
		this.mfgShortName = mfgShortName;
	}

	public StringProperty getUnitType() {
		return unitType;
	}

	public void setUnitType(StringProperty unitType) {
		this.unitType = unitType;
	}

	public StringProperty getMrp() {
		return mrp;
	}

	public void setMrp(StringProperty mrp) {
		this.mrp = mrp;
	}

	public StringProperty getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(StringProperty purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public StringProperty getQuantity() {
		return quantity;
	}

	public void setQuantity(StringProperty quantity) {
		this.quantity = quantity;
	}

	public StringProperty getMfgDate() {
		return mfgDate;
	}

	public void setMfgDate(StringProperty mfgDate) {
		this.mfgDate = mfgDate;
	}

	public StringProperty getExpDate() {
		return expDate;
	}

	public void setExpDate(StringProperty expDate) {
		this.expDate = expDate;
	}

	public StringProperty getUnitQuantity() {
		return unitQuantity;
	}

	public void setUnitQuantity(StringProperty unitQuantity) {
		this.unitQuantity = unitQuantity;
	}
	
	
}
