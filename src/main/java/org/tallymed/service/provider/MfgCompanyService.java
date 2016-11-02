package org.tallymed.service.provider;

import java.util.List;

import org.tallymed.service.model.MfgCompany;

public interface MfgCompanyService {
	public List<MfgCompany> findAll(MfgCompany mfgCompany);
	public List<MfgCompany> findById(MfgCompany mfgCompany);
	public void save(MfgCompany mfgCompany);
	public void delete(MfgCompany mfgCompany);
	public void update(MfgCompany mfgCompany);
}
