package org.tallymed.service.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tallymed.service.dao.OrderPurchaseProductDao;
import org.tallymed.service.model.OrderPurchaseProduct;

@Repository("orderPurchaseProduct")
public class OrderPurchaseProductDaoImpl extends GenericDaoImpl<OrderPurchaseProduct, Integer> implements OrderPurchaseProductDao {

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void saveOrderPurchaseProduct(OrderPurchaseProduct orderPurchaseProduct) {
		saveOrUpdate(orderPurchaseProduct);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void deleteOrderPurchaseProduct(OrderPurchaseProduct orderPurchaseProduct) {
		remove(orderPurchaseProduct);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void updateOrderPurchaseProduct(OrderPurchaseProduct orderPurchaseProduct) {
		update(orderPurchaseProduct);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public List<OrderPurchaseProduct> findOrderPurchaseProductByID(OrderPurchaseProduct orderPurchaseProduct) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public List<OrderPurchaseProduct> findAllOrderPurchaseProductByID(OrderPurchaseProduct orderPurchaseProduct) {
		return getAll();
	}

}
