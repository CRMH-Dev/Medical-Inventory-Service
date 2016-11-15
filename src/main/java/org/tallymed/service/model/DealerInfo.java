package org.tallymed.service.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "DEALER_INFO")
@NamedQueries({
	  @NamedQuery(name="DealerInfo.findAll", query="select D FROM DealerInfo D"),
	  @NamedQuery(name="DealerInfo.findByName", query="select D FROM DealerInfo D where D.dealerName=:dealerName")
})
public class DealerInfo {
	@GeneratedValue(generator="sqlite_DEALER_INFO")
	@TableGenerator(name="sqlite_DEALER_INFO", table="DEALER_INFO", initialValue=1, allocationSize=1)
	@Id
	@Column(name = "DEALER_ID")
	private int dealerId;
	@Column(name = "DEALER_COMPANY_NAME")
	private String dealerCompanyName;
	@Column(name = "DEALER_NAME")
	private String dealerName;
	@Column(name = "CONTACT_NO")
	private String contactNo;
	@Column(name = "TOTAL_PURCHASE")
	private float totalPurchase;
	@Column(name = "TOTAL_PAYMENT")
	private float totalPayment;
	@OneToMany(mappedBy = "dealerInfo", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<DealerPayment> dealerPayments;
	
	@OneToMany(mappedBy = "dealerInfo", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<OrderPurchase> orderPurchases;
	
	
	public Set<DealerPayment> getDealerPayments() {
		return dealerPayments;
	}
	public void setDealerPayments(Set<DealerPayment> dealerPayments) {
		this.dealerPayments = dealerPayments;
	}
	public int getDealerId() {
		return dealerId;
	}
	public void setDealerId(int dealerId) {
		this.dealerId = dealerId;
	}
	
	public String getDealerCompanyName() {
		return dealerCompanyName;
	}
	public void setDealerCompanyName(String dealerCompanyName) {
		this.dealerCompanyName = dealerCompanyName;
	}
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public float getTotalPurchase() {
		return totalPurchase;
	}
	public void setTotalPurchase(float totalPurchase) {
		this.totalPurchase = totalPurchase;
	}
	public float getTotalPayment() {
		return totalPayment;
	}
	public void setTotalPayment(float totalPayment) {
		this.totalPayment = totalPayment;
	}
	public Set<OrderPurchase> getOrderPurchases() {
		return orderPurchases;
	}
	public void setOrderPurchases(Set<OrderPurchase> orderPurchases) {
		this.orderPurchases = orderPurchases;
	}
}
