package org.tallymed.ui.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class UnitOfMeasurement {
	private IntegerProperty uomID;
	private IntegerProperty perTapQuantity;
	private StringProperty unitDescription;
	
	
	public IntegerProperty uomIDProperty() {
		return this.uomID;
	}
	
	public int getUomID() {
		return this.uomIDProperty().get();
	}
	
	public void setUomID(final int uomID) {
		this.uomIDProperty().set(uomID);
	}
	
	public IntegerProperty perTapQuantityProperty() {
		return this.perTapQuantity;
	}
	
	public int getPerTapQuantity() {
		return this.perTapQuantityProperty().get();
	}
	
	public void setPerTapQuantity(final int perTapQuantity) {
		this.perTapQuantityProperty().set(perTapQuantity);
	}
	
	public StringProperty unitDescriptionProperty() {
		return this.unitDescription;
	}
	
	public String getUnitDescription() {
		return this.unitDescriptionProperty().get();
	}
	
	public void setUnitDescription(final String unitDescription) {
		this.unitDescriptionProperty().set(unitDescription);
	}	
}
