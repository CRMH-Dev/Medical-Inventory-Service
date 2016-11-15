package org.tallymed.service.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tallymed.service.dao.MfgCompanyDao;
import org.tallymed.service.model.MfgCompany;

@Repository("mfgCompanyDao")
public class MfgCompanyDaoImpl extends GenericDaoImpl<MfgCompany, Integer> implements MfgCompanyDao {

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void saveMfgCompany(MfgCompany mfgCompany) {
		saveOrUpdate(mfgCompany);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void deleteMfgCompany(MfgCompany mfgCompany) {
		remove(mfgCompany);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void updateMfgCompany(MfgCompany mfgCompany) {
		saveOrUpdate(mfgCompany);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public List<MfgCompany> findMfgCompanyByID(MfgCompany mfgCompany) {
		return null;
	}

}
