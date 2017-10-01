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
@Table(name = "ORDER_SELL_PRODUCT")
@NamedQueries({
	  @NamedQuery(name="OrderSellProduct.findAll", query="select O FROM OrderSellProduct O")
})
public class OrderSellProduct {
	@GeneratedValue(generator="sqlite_ORDER_SELL_PRODUCT")
	@TableGenerator(name="sqlite_ORDER_SELL_PRODUCT", table="ORDER_SELL_PRODUCT", initialValue=1, allocationSize=1)
	@Id
	@Column(name = "ORDER_SELL_PRODUCT_ID")
	private int orderSellProductId;
	@ManyToOne(targetEntity = OrderSell.class)
	@JoinColumn(name = "ORDER_SELL_ID", nullable = false)
	private OrderSell orderSell;
	@Column(name = "PRODUCT_ID")
	private int productId;
	@Column(name = "QUANTITY")
	private int quantity;
	
	
	public int getOrderSellProductId() {
		return orderSellProductId;
	}
	public void setOrderSellProductId(int orderSellProductId) {
		this.orderSellProductId = orderSellProductId;
	}
	
	public OrderSell getOrderSell() {
		return orderSell;
	}
	public void setOrderSell(OrderSell orderSell) {
		this.orderSell = orderSell;
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
