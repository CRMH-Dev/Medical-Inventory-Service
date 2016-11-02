package org.tallymed.service.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.tallymed.service.dao.UOMDao;
import org.tallymed.service.model.UnitOfMeasurement;

@Repository("uomDao")
public class UOMDaoImpl extends GenericDaoImpl<UnitOfMeasurement, Integer> implements UOMDao {

	@Override
	public void saveUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement) {
		saveOrUpdate(unitOfMeasurement);
	}

	@Override
	public void deleteUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement) {
		remove(unitOfMeasurement);
	}

	@Override
	public void updateUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement) {
		saveOrUpdate(unitOfMeasurement);

	}

	@Override
	public List<UnitOfMeasurement> findUnitOfMeasurementByID(UnitOfMeasurement unitOfMeasurement) {
		return findUnitOfMeasurementByID(unitOfMeasurement);
	}

}
