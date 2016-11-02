package org.tallymed.service.provider;

import java.util.List;
import org.springframework.stereotype.Service;
import org.tallymed.service.dao.CustomerInfoDao;
import org.tallymed.service.model.CustomerInfo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Implements the business methods for the customer service
 * @author Rajdip
 */
@Service("customerInfoService")
public class CustomerInfoServiceImpl implements CustomerInfoService
{
    @Autowired
    private CustomerInfoDao customerInfoDao;

    @Override
    public CustomerInfo findById(long id)
    {
        return customerInfoDao.findCustomerInfo(id);
    }

    @Override
    public List<CustomerInfo> findAll()
    {
    	return customerInfoDao.getAllCustomerInfo();
    }

    @Override
    public void save( CustomerInfo customer )
    {
        customerInfoDao.saveCustomerInfo(customer);
    }

    @Override
    public void update( CustomerInfo customer )
    {
        customerInfoDao.updateCustomerInfo(customer);
    }

    @Override
    public void delete( CustomerInfo customer )
    {
        customerInfoDao.deleteCustomerInfo(customer);
    }
}
