package org.tallymed.service.dao;

import java.util.List;

import org.tallymed.service.model.LoginInformation;

public interface LoginDao {
	public void saveLoginInformation (LoginInformation loginInformation);
	public void deleteLoginInformation (LoginInformation loginInformation);
	public void updateLoginInformation (LoginInformation loginInformation);
	public List<LoginInformation> findLoginInfoByID(LoginInformation loginInformation);
}
