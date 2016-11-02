package org.tallymed.service.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.tallymed.service.dao.MfgCompanyDao;
import org.tallymed.service.model.MfgCompany;

@Repository("mfgCompanyDao")
public class MfgCompanyDaoImpl extends GenericDaoImpl<MfgCompany, Integer> implements MfgCompanyDao {

	@Override
	public void saveMfgCompany(MfgCompany mfgCompany) {
		saveOrUpdate(mfgCompany);
	}

	@Override
	public void deleteMfgCompany(MfgCompany mfgCompany) {
		remove(mfgCompany);
	}

	@Override
	public void updateMfgCompany(MfgCompany mfgCompany) {
		saveOrUpdate(mfgCompany);
	}

	@Override
	public List<MfgCompany> findMfgCompanyByID(MfgCompany mfgCompany) {
		return findMfgCompanyByID(mfgCompany);
	}

}
