package org.tallymed.service.dao;

import java.util.List;

import org.tallymed.service.model.CustomerInfo;

/**
 * Defines the data access methods for CustomerInfo persistence
 *
 * @author Rajdip
 */
public interface CustomerInfoDao
{
	public void saveCustomerInfo(CustomerInfo customerInfo);
    public void updateCustomerInfo(CustomerInfo customerInfo) ;
    public void deleteCustomerInfo(CustomerInfo customerInfo) ;
    public CustomerInfo findCustomerInfo(Long key);
    public List<CustomerInfo> getAllCustomerInfo() ;
}
