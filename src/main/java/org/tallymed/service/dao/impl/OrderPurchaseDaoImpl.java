package org.tallymed.service.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tallymed.service.dao.OrderPurchaseDao;
import org.tallymed.service.model.OrderPurchase;

@Repository("orderPurchase")
public class OrderPurchaseDaoImpl extends GenericDaoImpl<OrderPurchase, Integer> implements OrderPurchaseDao {

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void saveOrderPurchase(OrderPurchase orderPurchase) {
		saveOrUpdate(orderPurchase);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void deleteOrderPurchase(OrderPurchase orderPurchase) {
		remove(orderPurchase);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void updateOrderPurchase(OrderPurchase orderPurchase) {
		update(orderPurchase);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public List<OrderPurchase> findOrderPurchaseByID(OrderPurchase orderPurchase) {
		// TODO Auto-generated method stub
		return null;
	}

}
