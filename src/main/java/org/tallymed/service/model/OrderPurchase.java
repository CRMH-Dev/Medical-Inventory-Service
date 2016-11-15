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
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "ORDER_PURCHASE")
@NamedQueries({
	@NamedQuery(name="OrderPurchase.findAll", query="select O FROM OrderPurchase O"),
	@NamedQuery(name="OrderPurchase.findByInvoice", query="select O FROM OrderPurchase O where O.invoiceId=:invoiceId")
})
public class OrderPurchase {
	@GeneratedValue(generator="sqlite_ORDER_PURCHASE")
	@TableGenerator(name="sqlite_ORDER_PURCHASE", table="ORDER_PURCHASE", initialValue=1, allocationSize=1)
	@Id
	@Column(name = "ORDER_PURCHASE_ID")
	private int orderPurchaseId;
	@ManyToOne(targetEntity = DealerInfo.class)
    @JoinColumn(name = "DEALER_ID",nullable = false)
	private DealerInfo dealerInfo;
	@Column(name="INVOICE_ID")
	private String invoiceId;
	@Column(name = "ORDER_DATE")
	private Date orderDate;
	@Column(name = "ORDER_TOTAL")
	private float orderTotal;
	@OneToMany(mappedBy = "orderPurchase", cascade = CascadeType.ALL)
	private Set<OrderPurchaseProduct> orderPurchaseProducts;
	
	public int getOrderPurchaseId() {
		return orderPurchaseId;
	}
	public void setOrderPurchaseId(int orderPurchaseId) {
		this.orderPurchaseId = orderPurchaseId;
	}
	public DealerInfo getDealerId() {
		return dealerInfo;
	}
	public void setDealerId(DealerInfo dealerInfo) {
		this.dealerInfo = dealerInfo;
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
	public String getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}
	public Set<OrderPurchaseProduct> getOrderPurchaseProducts() {
		return orderPurchaseProducts;
	}
	public void setOrderPurchaseProducts(Set<OrderPurchaseProduct> orderPurchaseProducts) {
		this.orderPurchaseProducts = orderPurchaseProducts;
	}
}
