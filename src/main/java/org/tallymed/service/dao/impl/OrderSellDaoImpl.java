package org.tallymed.service.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tallymed.service.dao.OrderSellDao;
import org.tallymed.service.model.OrderSell;

@Repository("orderSell")
public class OrderSellDaoImpl extends GenericDaoImpl<OrderSell, Integer> implements OrderSellDao {

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void saveOrderSell(OrderSell orderSell) {
		saveOrUpdate(orderSell);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void deleteOrderSell(OrderSell orderSell) {
		remove(orderSell);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void updateOrderSell(OrderSell orderSell) {
		update(orderSell);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public List<OrderSell> findOrderSellByID(OrderSell orderSell) {
		// TODO Auto-generated method stub
		return null;
	}

}
