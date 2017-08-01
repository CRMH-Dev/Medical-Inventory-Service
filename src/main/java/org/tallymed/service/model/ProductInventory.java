package org.tallymed.service.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "PRODUCT_INVENTORY")
@NamedQueries({
	  @NamedQuery(name="ProductInventory.findAll", query="select P FROM ProductInventory P"),
	  @NamedQuery(name="ProductInventory.findByBatchId", query="select P FROM ProductInventory P where P.batchId=:batchId"),
	  @NamedQuery(name="ProductInventory.findByAnyKey", query="select PI FROM ProductInventory PI where PI.batchId=:batchId and PI.product.productName=:productName")
})
public class ProductInventory {
	
	@GeneratedValue(generator="sqlite_PRODUCT_INVENTORY")
	@TableGenerator(name="sqlite_PRODUCT_INVENTORY", table="PRODUCT_INVENTORY", initialValue=1, allocationSize=1)
	@Id
	@Column(name = "INVENTORY_ID")
	private int inventoryId;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PRODUCT_ID",nullable = false)
	private Product product;
	
	@Column(name = "BATCH_ID")
	private String batchId;
	@Column(name = "MFG_DATE")
	private Date mfgDate;
	@Column(name = "EXPIRY_DATE")
	private Date epiryDate;
	@Column(name = "PURCHASE_PRICE")
	private float purchasePrice;
	@Column(name = "SELLING_PRICE")
	private float sellPrice;
	@Column(name = "CURRENT_STOCK")
	private int currentStock;
	@Column(name = "DATE_OF_PURCHASE")
	private Date dateOfPurchase;
	
	public int getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}	
	public String getBatchId() {
		return batchId;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	public Date getMfgDate() {
		return mfgDate;
	}
	public void setMfgDate(Date mfgDate) {
		this.mfgDate = mfgDate;
	}
	public Date getEpiryDate() {
		return epiryDate;
	}
	public void setEpiryDate(Date epiryDate) {
		this.epiryDate = epiryDate;
	}
	public float getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(float purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	public float getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(float sellPrice) {
		this.sellPrice = sellPrice;
	}
	public int getCurrentStock() {
		return currentStock;
	}
	public void setCurrentStock(int currentStock) {
		this.currentStock = currentStock;
	}
	public Date getDateOfPurchase() {
		return dateOfPurchase;
	}
	public void setDateOfPurchase(Date dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}
}
