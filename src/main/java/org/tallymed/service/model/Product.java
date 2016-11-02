package org.tallymed.service.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "PRODUCT")
@NamedQueries({
	  @NamedQuery(name="Product.findAll", query="select P FROM Product P"),
	  @NamedQuery(name="Product.findByProductName", query="select P FROM Product P where P.productName=:productName"),
	  @NamedQuery(name="Product.findByAnyValue", query="select P FROM Product P where P.productName=:productName or P.productComposition=:productComposition")
})
public class Product {
	@GeneratedValue(generator="sqlite_PRODUCT")
	@TableGenerator(name="sqlite_PRODUCT", table="PRODUCT", initialValue=1, allocationSize=1)
	@Id
	@Column(name = "PRODUCT_ID")
	private int productId;
	@ManyToOne
    @JoinColumn(name = "UOM_ID",nullable = false)
	private UnitOfMeasurement unitOfMeasurement;
	@ManyToOne
    @JoinColumn(name = "COMPANY_ID",nullable = false)
	private MfgCompany mfgCompany;
	@Column(name = "PRODUCT_NAME")
	private String productName;
	@Column(name = "PRODUCT_COMPOSITION")
	private String productComposition;
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private Set<ProductInventory> productInventorys;
	
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public UnitOfMeasurement getUnitOfMeasurement() {
		return unitOfMeasurement;
	}
	public void setUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement) {
		this.unitOfMeasurement = unitOfMeasurement;
	}
	
	public MfgCompany getMfgCompany() {
		return mfgCompany;
	}
	public void setMfgCompany(MfgCompany mfgCompany) {
		this.mfgCompany = mfgCompany;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductComposition() {
		return productComposition;
	}
	public void setProductComposition(String productComposition) {
		this.productComposition = productComposition;
	}
	public Set<ProductInventory> getProductInventorys() {
		return productInventorys;
	}
	public void setProductInventorys(Set<ProductInventory> productInventorys) {
		this.productInventorys = productInventorys;
	}
	
}
