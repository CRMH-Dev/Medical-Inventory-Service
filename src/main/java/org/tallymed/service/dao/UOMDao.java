package org.tallymed.service.dao;

import java.util.List;

import org.tallymed.service.model.UnitOfMeasurement;

public interface UOMDao {
	public void saveUnitOfMeasurement (UnitOfMeasurement unitOfMeasurement);
	public void deleteUnitOfMeasurement (UnitOfMeasurement unitOfMeasurement);
	public void updateUnitOfMeasurement (UnitOfMeasurement unitOfMeasurement);
	public List<UnitOfMeasurement> findUnitOfMeasurementByID(UnitOfMeasurement unitOfMeasurement);
}
