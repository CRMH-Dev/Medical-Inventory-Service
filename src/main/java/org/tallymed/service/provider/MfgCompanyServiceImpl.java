package org.tallymed.service.provider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tallymed.service.dao.MfgCompanyDao;
import org.tallymed.service.model.MfgCompany;

@Service("mfgCompanyService")
public class MfgCompanyServiceImpl implements MfgCompanyService {

	@Autowired
	MfgCompanyDao mfgCompanyDao;
	
	@Override
	public List<MfgCompany> findAll(MfgCompany mfgCompany) {
		return mfgCompanyDao.findMfgCompanyByID(mfgCompany);
	}

	@Override
	public List<MfgCompany> findById(MfgCompany mfgCompany) {
		return mfgCompanyDao.findMfgCompanyByID(mfgCompany);
	}

	@Override
	public void save(MfgCompany mfgCompany) {
		mfgCompanyDao.saveMfgCompany(mfgCompany);

	}

	@Override
	public void delete(MfgCompany mfgCompany) {
		mfgCompanyDao.deleteMfgCompany(mfgCompany);

	}

	@Override
	public void update(MfgCompany mfgCompany) {
		mfgCompanyDao.updateMfgCompany(mfgCompany);

	}

	public MfgCompanyDao getMfgCompanyDao() {
		return mfgCompanyDao;
	}

	public void setMfgCompanyDao(MfgCompanyDao mfgCompanyDao) {
		this.mfgCompanyDao = mfgCompanyDao;
	}
	
}
