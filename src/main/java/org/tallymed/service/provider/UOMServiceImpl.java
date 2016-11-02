package org.tallymed.service.provider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tallymed.service.dao.UOMDao;
import org.tallymed.service.model.UnitOfMeasurement;

@Service("uomService")
public class UOMServiceImpl implements UOMService {

	@Autowired
	UOMDao uomDao;
	
	@Override
	public List<UnitOfMeasurement> findAll(UnitOfMeasurement unitOfMeasurement) {
		return uomDao.findUnitOfMeasurementByID(unitOfMeasurement);
	}

	@Override
	public List<UnitOfMeasurement> findById(UnitOfMeasurement unitOfMeasurement) {
		return uomDao.findUnitOfMeasurementByID(unitOfMeasurement);
	}

	@Override
	public void save(UnitOfMeasurement unitOfMeasurement) {
		uomDao.saveUnitOfMeasurement(unitOfMeasurement);

	}

	@Override
	public void delete(UnitOfMeasurement unitOfMeasurement) {
		uomDao.deleteUnitOfMeasurement(unitOfMeasurement);

	}

	@Override
	public void update(UnitOfMeasurement unitOfMeasurement) {
		uomDao.updateUnitOfMeasurement(unitOfMeasurement);

	}

	public UOMDao getUnitOfMeasurementDao() {
		return uomDao;
	}

	public void setUnitOfMeasurementDao(UOMDao uomDao) {
		this.uomDao = uomDao;
	}
	
}
