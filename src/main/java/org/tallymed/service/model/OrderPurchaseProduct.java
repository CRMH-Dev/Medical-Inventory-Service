package org.tallymed.service.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
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
	@ManyToOne(targetEntity = OrderPurchase.class)
    @JoinColumn(name = "ORDER_PURCHASE_ID",nullable = false)
	private OrderPurchase orderPurchase;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "INVENTORY_ID",nullable = false)
	private ProductInventory productInventory;
	@Column(name = "QUANTITY")
	private int quantity;
	
	
	public int getOrderPurchaseProductId() {
		return orderPurchaseProductId;
	}
	public void setOrderPurchaseProductId(int orderPurchaseProductId) {
		this.orderPurchaseProductId = orderPurchaseProductId;
	}
	
	public OrderPurchase getOrderPurchase() {
		return orderPurchase;
	}
	public void setOrderPurchase(OrderPurchase orderPurchase) {
		this.orderPurchase = orderPurchase;
	}
	public ProductInventory getProductInventory() {
		return productInventory;
	}
	public void setProductInventory(ProductInventory productInventory) {
		this.productInventory = productInventory;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
