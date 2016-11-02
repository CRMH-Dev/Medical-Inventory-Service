package org.tallymed.service.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tallymed.service.dao.CustomerInfoDao;
import org.tallymed.service.model.CustomerInfo;

/**
 * Implements the data access methods for CustomerInfo persistence
 *
 * @author Rajdip
 */

@Repository("customerInfoDao")
public class CustomerInfoDaoImpl extends GenericDaoImpl<CustomerInfo, Long> implements CustomerInfoDao {

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void saveCustomerInfo(CustomerInfo customerInfo) {
		saveOrUpdate(customerInfo);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void updateCustomerInfo(CustomerInfo customerInfo) {
		update(customerInfo);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void deleteCustomerInfo(CustomerInfo customerInfo) {
		remove(customerInfo);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public CustomerInfo findCustomerInfo(Long key) {
		return findCustomerInfo(key);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public List<CustomerInfo> getAllCustomerInfo() {
		return getAll();
	}

}
