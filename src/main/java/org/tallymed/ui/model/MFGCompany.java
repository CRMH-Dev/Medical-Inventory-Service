package org.tallymed.ui.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class MFGCompany {
	private IntegerProperty companyID;
	private StringProperty companyName;
	private StringProperty companyShortName;
	private StringProperty location;
	
	public IntegerProperty companyIDProperty() {
		return this.companyID;
	}
	
	public int getCompanyID() {
		return this.companyIDProperty().get();
	}
	
	public void setCompanyID(final int companyID) {
		this.companyIDProperty().set(companyID);
	}
	
	public StringProperty companyNameProperty() {
		return this.companyName;
	}
	
	public String getCompanyName() {
		return this.companyNameProperty().get();
	}
	
	public void setCompanyName(final String companyName) {
		this.companyNameProperty().set(companyName);
	}
	
	public StringProperty companyShortNameProperty() {
		return this.companyShortName;
	}
	
	public String getCompanyShortName() {
		return this.companyShortNameProperty().get();
	}
	
	public void setCompanyShortName(final String companyShortName) {
		this.companyShortNameProperty().set(companyShortName);
	}

	public StringProperty locationProperty() {
		return this.location;
	}
	

	public String getLocation() {
		return this.locationProperty().get();
	}
	

	public void setLocation(final String location) {
		this.locationProperty().set(location);
	}
	
	
	
}
