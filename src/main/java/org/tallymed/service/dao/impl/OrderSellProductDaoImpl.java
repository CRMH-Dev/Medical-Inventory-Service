package org.tallymed.service.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tallymed.service.dao.OrderSellProductDao;
import org.tallymed.service.model.OrderSellProduct;

@Repository("orderSellProduct")
public class OrderSellProductDaoImpl extends GenericDaoImpl<OrderSellProduct, Integer> implements OrderSellProductDao {

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void saveOrderSellProduct(OrderSellProduct orderSellProduct) {
		saveOrUpdate(orderSellProduct);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void deleteOrderSellProduct(OrderSellProduct orderSellProduct) {
		remove(orderSellProduct);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void updateOrderSellProduct(OrderSellProduct orderSellProduct) {
		update(orderSellProduct);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public List<OrderSellProduct> findOrderSellProductByID(OrderSellProduct orderSellProduct) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public List<OrderSellProduct> findAllOrderSellProductByID(OrderSellProduct orderSellProduct) {
		return getAll();
	}

}
