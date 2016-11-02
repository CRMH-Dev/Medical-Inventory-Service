package org.tallymed.service.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
/**
 * 
 * @author Rajdip
 *
 */
@Entity
@Table(name = "CUSTOMER_INFO")
@NamedQueries({
	  @NamedQuery(name="CustomerInfo.byID", query="FROM CustomerInfo")
})
public class CustomerInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@GeneratedValue(generator="sqlite_CUSTOMER_INFO")
	@TableGenerator(name="sqlite_CUSTOMER_INFO", table="CUSTOMER_INFO", initialValue=1, allocationSize=1)
	@Id
    @Column(name = "CUSTOMER_ID")
	private int customerId;
	
	@Column(name = "CUSTOMER_NAME")
	private String customerName;
	
	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "GENDER")
	private String gender;
	
	@Column(name = "CONTACT_NO")
	private String contactNo;
	
	@Column(name = "CURRENT_BALANCE")
	private float currentBalance;
	
	@Column(name = "LAST_PAYMENT")
	private float lastPayment;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}



	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public float getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(float currentBalance) {
		this.currentBalance = currentBalance;
	}

	@Override
	public String toString() {
		return "CustomerInfo [customerId=" + customerId + ", customerName=" + customerName + ", address=" + address
				+ ", gender=" + gender + ", contactNo=" + contactNo + ", currentBalance=" + currentBalance
				+ ", lastPayment=" + lastPayment + "]";
	}

	public float getLastPayment() {
		return lastPayment;
	}

	public void setLastPayment(float lastPayment) {
		this.lastPayment = lastPayment;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
}
