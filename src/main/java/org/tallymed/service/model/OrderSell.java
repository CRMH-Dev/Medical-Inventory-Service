package org.tallymed.service.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "ORDER_SELL")
@NamedQueries({
	  @NamedQuery(name="OrderSell.findAll", query="select O FROM OrderSell O")
})
public class OrderSell {
	@GeneratedValue(generator="sqlite_ORDER_SELL")
	@TableGenerator(name="sqlite_ORDER_SELL", table="ORDER_SELL", initialValue=1, allocationSize=1)
	@Id
	@Column(name = "ORDER_SELL_ID")
	private int orderSellId;
	@Column(name = "CUSTOMER_ID")
	private int customerId;
	@Column(name = "ORDER_DATE")
	private Date orderDate;
	@Column(name = "ORDER_TOTAL")
	private float orderTotal;
	
	public int getOrderSellId() {
		return orderSellId;
	}
	public void setOrderSellId(int orderSellId) {
		this.orderSellId = orderSellId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public float getOrderTotal() {
		return orderTotal;
	}
	public void setOrderTotal(float orderTotal) {
		this.orderTotal = orderTotal;
	}
}
