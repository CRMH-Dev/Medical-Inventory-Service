package org.tallymed.service.provider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tallymed.service.dao.ProductDao;
import org.tallymed.service.model.Product;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;
	
	@Override
	public List<Product> findAll(Product product) {
		return productDao.findProductByID(product);
	}

	@Override
	public List<Product> findById(Product product) {
		return productDao.findProductByID(product);
	}

	@Override
	public void save(Product product) {
		productDao.saveProduct(product);

	}

	@Override
	public void delete(Product product) {
		productDao.deleteProduct(product);

	}

	@Override
	public void update(Product product) {
		productDao.updateProduct(product);

	}

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
}
