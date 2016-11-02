package org.tallymed.service.provider;


import java.util.List;

import org.tallymed.service.model.ProductInventory;;

public interface ProductInventoryService {
	public List<ProductInventory> findAll(ProductInventory productInventory);
	public List<ProductInventory> findById(ProductInventory productInventory);
	public void save(ProductInventory productInventory);
	public void delete(ProductInventory productInventory);
	public void update(ProductInventory productInventory);
}
