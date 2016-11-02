package org.tallymed.service.provider;

import java.util.List;

import org.tallymed.service.model.UnitOfMeasurement;

public interface UOMService {
	public List<UnitOfMeasurement> findAll(UnitOfMeasurement unitOfMeasurement);
	public List<UnitOfMeasurement> findById(UnitOfMeasurement unitOfMeasurement);
	public void save(UnitOfMeasurement unitOfMeasurement);
	public void delete(UnitOfMeasurement unitOfMeasurement);
	public void update(UnitOfMeasurement unitOfMeasurement);
}
