package org.tallymed.service.provider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tallymed.service.dao.ProductInventoryDao;
import org.tallymed.service.model.ProductInventory;

@Service("productInventoryService")
public class ProductInventoryServiceImpl implements ProductInventoryService {

	@Autowired
	ProductInventoryDao productInventoryDao;
	
	@Override
	public List<ProductInventory> findAll(ProductInventory productInventory) {
		return productInventoryDao.findAllProductInventory(productInventory);
	}

	@Override
	public List<ProductInventory> findById(ProductInventory productInventory) {
		return productInventoryDao.findProductInventoryByID(productInventory);
	}

	@Override
	public void save(ProductInventory productInventory) {
		productInventoryDao.saveProductInventory(productInventory);

	}

	@Override
	public void delete(ProductInventory productInventory) {
		productInventoryDao.deleteProductInventory(productInventory);

	}

	@Override
	public void update(ProductInventory productInventory) {
		productInventoryDao.updateProductInventory(productInventory);

	}

	public ProductInventoryDao getProductInventoryDao() {
		return productInventoryDao;
	}

	public void setProductInventoryDao(ProductInventoryDao productInventoryDao) {
		this.productInventoryDao = productInventoryDao;
	}
	
}
