package org.tallymed.service.clientserv.op;

import java.util.List;

import org.tallymed.service.clientserv.type.OperationType;

public class DealerOperation {
	private String delaerName;
	private String dealerCompany;
	private String contactNo;
	private OperationType operationType;
	private List<String> dealersName;
	
	public String getDelaerName() {
		return delaerName;
	}
	public void setDelaerName(String delaerName) {
		this.delaerName = delaerName;
	}
	public String getDealerCompany() {
		return dealerCompany;
	}
	public void setDealerCompany(String dealerCompany) {
		this.dealerCompany = dealerCompany;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public OperationType getOperationType() {
		return operationType;
	}
	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}
	public List<String> getDealersName() {
		return dealersName;
	}
	public void setDealersName(List<String> dealersName) {
		this.dealersName = dealersName;
	}
	
}
