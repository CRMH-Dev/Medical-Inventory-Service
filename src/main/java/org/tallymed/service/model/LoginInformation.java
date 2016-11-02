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
@Table(name = "LOGIN_INFORMATION")
@NamedQueries({
	  @NamedQuery(name="LoginInformation.validateUser", query="select L FROM LoginInformation L where L.username=:username and L.password=:password")
})
public class LoginInformation {
	@Override
	public String toString() {
		return "LoginInformation [loginId=" + loginId + ", username=" + username + ", password=" + password + ", fname="
				+ fname + ", lname=" + lname + ", isAdmin=" + isAdmin + ", contactNo=" + contactNo + "]";
	}

	@GeneratedValue(generator="sqlite_LOGIN_INFORMATION")
	@TableGenerator(name="sqlite_LOGIN_INFORMATION", table="LOGIN_INFORMATION", initialValue=1, allocationSize=1)
	@Id
    @Column(name = "LOGIN_ID")
	private int loginId;
	
	@Column(name = "USERNAME")
	private String username;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "FIRSTNAME")
	private String fname;
	
	@Column(name = "LASTNAME")
	private String lname;
	
	@Column(name = "IS_ADMIN")
	private boolean isAdmin;

	@Column(name = "CONTACT_NO")
	private String contactNo;

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
}
