package org.tallymed.service.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tallymed.service.dao.ProductInventoryDao;
import org.tallymed.service.model.ProductInventory;

@Repository("productInventoryDao")
public class ProductInventoryDaoImpl extends GenericDaoImpl<ProductInventory, Integer> implements ProductInventoryDao {

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void saveProductInventory(ProductInventory productInventory) {
		saveOrUpdate(productInventory);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void deleteProductInventory(ProductInventory productInventory) {
		remove(productInventory);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void updateProductInventory(ProductInventory productInventory) {
		update(productInventory);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public List<ProductInventory> findProductInventoryByID(ProductInventory productInventory) {
		// TODO Auto-generated method stub
		return null;
	}

}
