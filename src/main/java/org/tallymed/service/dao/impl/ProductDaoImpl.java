package org.tallymed.service.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tallymed.service.dao.ProductDao;
import org.tallymed.service.model.Product;

@Repository("productDao")
public class ProductDaoImpl extends GenericDaoImpl<Product, Integer> implements ProductDao {
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void saveProduct(Product product) {
		saveOrUpdate(product);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void deleteProduct(Product product) {
		remove(product);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void updateProduct(Product product) {
		update(product);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public List<Product> findProductByID(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

}
