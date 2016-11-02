package org.tallymed.service.dao;

import java.util.List;

import org.tallymed.service.model.MfgCompany;

public interface MfgCompanyDao {
	public void saveMfgCompany (MfgCompany mfgCompany);
	public void deleteMfgCompany (MfgCompany mfgCompany);
	public void updateMfgCompany (MfgCompany mfgCompany);
	public List<MfgCompany> findMfgCompanyByID(MfgCompany mfgCompany);
}
