package org.tallymed.service.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "CUSTOMER_PAYMENT")
@NamedQueries({
	  @NamedQuery(name="CustomerPayment.fetchAll", query="select C FROM CustomerPayment C"),
	  @NamedQuery(name="CustomerPayment.fetchCustId", query="select C FROM CustomerPayment C where C.customerId=:customerId")
})
public class CustomerPayment {
	
	@GeneratedValue(generator="sqlite_CUSTOMER_PAYMENT")
	@TableGenerator(name="sqlite_CUSTOMER_PAYMENT", table="CUSTOMER_PAYMENT", initialValue=1, allocationSize=1)
	@Id
	@Column(name = "PAYMENT_ID")
	private int paymentId;
	@Column(name = "CUSTOMER_ID")
	private int customerId;
	@Column(name = "IS_PAYMENT")
	private boolean isPayment; //true if credit
	@Column(name = "AMOUNT")
	private float amount;
	
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public boolean isPayment() {
		return isPayment;
	}
	public void setPayment(boolean isPayment) {
		this.isPayment = isPayment;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
}
