package org.tallymed.service.provider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tallymed.service.dao.LoginDao;
import org.tallymed.service.model.LoginInformation;

@Service("loginInformationService")
public class LoginInformationServiceImpl implements LoginInformationService {

	@Autowired
	private LoginDao loginDao;
	
	@Override
	public List<LoginInformation> validateLogin(LoginInformation loginInformation) {
		return loginDao.findLoginInfoByID(loginInformation);
	}

	@Override
	public void save(LoginInformation loginInformation) {
		loginDao.saveLoginInformation(loginInformation);
	}

	@Override
	public void delete(LoginInformation loginInformation) {
		loginDao.deleteLoginInformation(loginInformation);
	}

	@Override
	public void update(LoginInformation loginInformation) {
		loginDao.updateLoginInformation(loginInformation);
	}

	public LoginDao getLoginDao() {
		return loginDao;
	}

	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}

}
