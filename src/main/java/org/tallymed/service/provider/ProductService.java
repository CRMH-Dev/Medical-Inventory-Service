package org.tallymed.service.provider;

import java.util.List;

import org.tallymed.service.model.Product;

public interface ProductService {
	public List<Product> findAll(Product product);
	public List<Product> findById(Product product);
	public void save(Product product);
	public void delete(Product product);
	public void update(Product product);
}
