package org.tallymed.ui.views.op;

import java.util.Date;
import java.util.List;

import org.tallymed.ui.views.op.type.OperationType;
import org.tallymed.ui.views.op.type.ProductOperationType;

public class ProductInventoryOperation {
	List<Products> products;
	private String invoiceID;
	private Date dateOfPurchase;
	private String dealerName;
	private OperationType operationType;
	private ProductOperationType productOperationType;
	
	public Date getDateOfPurchase() {
		return dateOfPurchase;
	}
	public void setDateOfPurchase(Date dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}
	public OperationType getOperationType() {
		return operationType;
	}
	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}
	public ProductOperationType getProductOperationType() {
		return productOperationType;
	}
	public void setProductOperationType(ProductOperationType productOperationType) {
		this.productOperationType = productOperationType;
	}
	public List<Products> getProducts() {
		return products;
	}
	public void setProducts(List<Products> products) {
		this.products = products;
	}
	public String getInvoiceID() {
		return invoiceID;
	}
	public void setInvoiceID(String invoiceID) {
		this.invoiceID = invoiceID;
	}
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	
	
	
}
