package org.tallymed.service.provider;

import java.util.List;

import org.tallymed.service.model.LoginInformation;

public interface LoginInformationService {
	public List<LoginInformation> validateLogin(LoginInformation loginInformation);
	public void save(LoginInformation loginInformation);
	public void delete(LoginInformation loginInformation);
	public void update(LoginInformation loginInformation);
}
