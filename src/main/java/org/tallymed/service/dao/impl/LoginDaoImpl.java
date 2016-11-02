package org.tallymed.service.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tallymed.service.dao.LoginDao;
import org.tallymed.service.model.LoginInformation;

@Repository("loginInfoDao")
public class LoginDaoImpl extends GenericDaoImpl<LoginInformation, Integer> implements LoginDao {

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public List<LoginInformation> findLoginInfoByID(LoginInformation loginInformation) {
		Query query = getSessionFactory()
						.getCurrentSession()
						.getNamedQuery("LoginInformation.validateUser")
						.setParameter("username", loginInformation.getUsername())
						.setParameter("password", loginInformation.getPassword());
		return findByNamedQuery(query);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void saveLoginInformation(LoginInformation loginInformation) {
		saveOrUpdate(loginInformation);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void deleteLoginInformation(LoginInformation loginInformation) {
		remove(loginInformation);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void updateLoginInformation(LoginInformation loginInformation) {
		update(loginInformation);
	}
}
