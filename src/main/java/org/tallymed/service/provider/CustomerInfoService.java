package org.tallymed.service.provider;

import java.util.List;

import org.tallymed.service.model.CustomerInfo;

/**
 * Defines the business methods for the customer service
 *
 * @author Rajdip
 */
public interface CustomerInfoService
{
    public CustomerInfo findById(long id);
    public List<CustomerInfo> findAll();
    public void save(CustomerInfo customer);
    public void update(CustomerInfo customer);
    public void delete(CustomerInfo customer);
}
