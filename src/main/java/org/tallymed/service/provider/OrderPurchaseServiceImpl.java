package org.tallymed.service.provider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tallymed.service.dao.OrderPurchaseDao;
import org.tallymed.service.model.OrderPurchase;

@Service("orderPurchaseService")
public class OrderPurchaseServiceImpl implements OrderPurchaseService {

	@Autowired
	OrderPurchaseDao orderPurchaseDao;
	
	@Override
	public List<OrderPurchase> findAll(OrderPurchase orderPurchase) {
		// TODO Auto-generated method stub
		return orderPurchaseDao.findOrderPurchaseByID(orderPurchase);
	}

	@Override
	public List<OrderPurchase> findById(OrderPurchase orderPurchase) {
		// TODO Auto-generated method stub
		return orderPurchaseDao.findOrderPurchaseByID(orderPurchase);
	}

	@Override
	public void save(OrderPurchase orderPurchase) {
		orderPurchaseDao.saveOrderPurchase(orderPurchase);
		
	}

	@Override
	public void delete(OrderPurchase orderPurchase) {
		orderPurchaseDao.deleteOrderPurchase(orderPurchase);
	}

	@Override
	public void update(OrderPurchase orderPurchase) {
		orderPurchaseDao.updateOrderPurchase(orderPurchase);
	}

	public OrderPurchaseDao getOrderPurchaseDao() {
		return orderPurchaseDao;
	}

	public void setOrderPurchaseDao(OrderPurchaseDao orderPurchaseDao) {
		this.orderPurchaseDao = orderPurchaseDao;
	}

}
