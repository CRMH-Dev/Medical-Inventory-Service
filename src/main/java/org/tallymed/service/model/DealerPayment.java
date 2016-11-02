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
@Table(name = "DEALER_PAYMENT")
@NamedQueries({
	  @NamedQuery(name="DealerPayment.findAll", query="select D FROM DealerPayment D")
})
public class DealerPayment {
	@GeneratedValue(generator="sqlite_DEALER_PAYMENT")
	@TableGenerator(name="sqlite_DEALER_PAYMENT", table="DEALER_PAYMENT", initialValue=1, allocationSize=1)
	@Id
	@Column(name = "PAYMENT_ID")
	private int paymentId;
	@Column(name = "DEALER_ID")
	private int dealerId;
	@Column(name = "IS_PAYMENT")
	private boolean isPayment;
	@Column(name = "AMOUNT")
	private float amount;
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public int getDealerId() {
		return dealerId;
	}
	public void setDealerId(int dealerId) {
		this.dealerId = dealerId;
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
