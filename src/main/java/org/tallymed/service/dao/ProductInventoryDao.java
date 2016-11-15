package org.tallymed.service.dao;

import java.util.List;

import org.tallymed.service.model.ProductInventory;


public interface ProductInventoryDao {
	public void saveProductInventory (ProductInventory productInventory);
	public void deleteProductInventory (ProductInventory productInventory);
	public void updateProductInventory (ProductInventory productInventory);
	public List<ProductInventory> findProductInventoryByID(ProductInventory productInventory);
	public List<ProductInventory> findAllProductInventory(ProductInventory productInventory);
}
