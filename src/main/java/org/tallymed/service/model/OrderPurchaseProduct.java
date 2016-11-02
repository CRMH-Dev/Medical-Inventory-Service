package org.tallymed.service.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "ORDER_PURCHASE_PRODUCT")
@NamedQueries({
	  @NamedQuery(name="OrderPurchaseProduct.findAll", query="select O FROM OrderPurchaseProduct O")
})
public class OrderPurchaseProduct {
	@GeneratedValue(generator="sqlite_ORDER_PURCHASE_PRODUCT")
	@TableGenerator(name="sqlite_ORDER_PURCHASE_PRODUCT", table="ORDER_PURCHASE_PRODUCT", initialValue=1, allocationSize=1)
	@Id
	@Column(name = "ORDER_PURCHASE_PRODUCT_ID")
	private int orderPurchaseProductId;
	@ManyToOne
    @JoinColumn(name = "ORDER_PURCHASE_ID",nullable = false)
	private int orderPurchaseId;
	@ManyToOne
    @JoinColumn(name = "INVENTORY_ID",nullable = false)
	private int productId;
	@Column(name = "QUANTITY")
	private int quantity;
	
	
	public int getOrderPurchaseProductId() {
		return orderPurchaseProductId;
	}
	public void setOrderPurchaseProductId(int orderPurchaseProductId) {
		this.orderPurchaseProductId = orderPurchaseProductId;
	}
	public int getOrderPurchaseId() {
		return orderPurchaseId;
	}
	public void setOrderPurchaseId(int orderPurchaseId) {
		this.orderPurchaseId = orderPurchaseId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
