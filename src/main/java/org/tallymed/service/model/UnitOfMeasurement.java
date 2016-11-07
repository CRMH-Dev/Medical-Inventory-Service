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
@Table(name = "UNIT_OF_MEASUREMENT")
@NamedQueries({
	  @NamedQuery(name="UnitOfMeasurement.findAll", query="select U FROM UnitOfMeasurement U"),
	  @NamedQuery(name="UnitOfMeasurement.findByParam", query="select U FROM UnitOfMeasurement U where U.unitType=:unitType and U.unitQuantity=:unitQuantity")
})
public class UnitOfMeasurement {
	
	@GeneratedValue(generator="sqlite_UNIT_OF_MEASUREMENT")
	@TableGenerator(name="sqlite_UNIT_OF_MEASUREMENT", table="UNIT_OF_MEASUREMENT", initialValue=1, allocationSize=1)
	@Id
	@Column(name = "UOM_ID")
	private int uomId;
	@Column(name = "UNIT_TYPE")
	private String unitType;
	@Column(name = "UNIT_QUANTITY")
	private int unitQuantity;
	@OneToMany(mappedBy = "unitOfMeasurement", cascade = CascadeType.ALL)
	private Set<Product> products;
	
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	public int getUomId() {
		return uomId;
	}
	public void setUomId(int uomId) {
		this.uomId = uomId;
	}
	public String getUnitType() {
		return unitType;
	}
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
	public int getUnitQuantity() {
		return unitQuantity;
	}
	public void setUnitQuantity(int unitQuantity) {
		this.unitQuantity = unitQuantity;
	}
}
