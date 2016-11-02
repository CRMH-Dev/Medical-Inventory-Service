package org.tallymed.service.dao;

import java.util.List;

import org.tallymed.service.model.Product;

public interface ProductDao {
	public void saveProduct (Product product);
	public void deleteProduct (Product product);
	public void updateProduct (Product product);
	public List<Product> findProductByID(Product product);
}
