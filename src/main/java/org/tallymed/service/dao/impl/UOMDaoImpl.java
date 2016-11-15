package org.tallymed.service.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tallymed.service.dao.UOMDao;
import org.tallymed.service.model.UnitOfMeasurement;

@Repository("uomDao")
public class UOMDaoImpl extends GenericDaoImpl<UnitOfMeasurement, Integer> implements UOMDao {

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void saveUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement) {
		saveOrUpdate(unitOfMeasurement);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void deleteUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement) {
		remove(unitOfMeasurement);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void updateUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement) {
		update(unitOfMeasurement);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public List<UnitOfMeasurement> findUnitOfMeasurementByID(UnitOfMeasurement unitOfMeasurement) {
		Query query = getSessionFactory()
				.getCurrentSession()
				.getNamedQuery("UnitOfMeasurement.findByParam")
				.setParameter("unitType",unitOfMeasurement.getUnitType())
				.setParameter("unitQuantity", unitOfMeasurement.getUnitQuantity());
		return findByNamedQuery(query);
	}

}
