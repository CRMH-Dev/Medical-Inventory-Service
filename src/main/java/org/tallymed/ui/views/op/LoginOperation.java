package org.tallymed.ui.views.op;

import org.tallymed.ui.views.op.type.OperationType;

public class LoginOperation {
private int loginId;
	
	private String username;
	
	private String password;
	
	private String fname;
	
	private String lname;
	
	private boolean isAdmin;

	private String contactNo;
	
	private OperationType operationType;
	
	public OperationType getOperationType() {
		return operationType;
	}

	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}
	
	public int getLoginId() {
		return loginId;
	}

	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	@Override
	public String toString() {
		return "{loginId=" + loginId + ", username=" + username + ", password=" + password + ", fname="
				+ fname + ", lname=" + lname + ", isAdmin=" + isAdmin + ", contactNo=" + contactNo + "}";
	}
	
	
}
