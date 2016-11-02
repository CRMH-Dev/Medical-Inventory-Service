package org.tallymed.service.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "MFG_COMPANY")
@NamedQueries({
	  @NamedQuery(name="MfgCompany.findAll", query="select M FROM MfgCompany M")
})
public class MfgCompany {
	
	@GeneratedValue(generator="sqlite_MFG_COMPANY")
	@TableGenerator(name="sqlite_MFG_COMPANY", table="MFG_COMPANY", initialValue=1, allocationSize=1)
	@Id
	@Column(name = "COMPANY_ID")
	private int companyId;
	@Column(name = "COMPANY_NAME")
	private String companyName;
	@Column(name = "COMPANY_SHORT_NAME")
	private String companyShortName;
	@Column(name = "ADDRESS")
	private String address;
	@OneToMany(mappedBy = "mfgCompany", cascade = CascadeType.ALL)
	private Set<Product> products;
	
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyShortName() {
		return companyShortName;
	}
	public void setCompanyShortName(String companyShortName) {
		this.companyShortName = companyShortName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
}
