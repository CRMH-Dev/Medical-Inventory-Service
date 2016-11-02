package org.tallymed.service.provider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tallymed.service.dao.OrderPurchaseProductDao;
import org.tallymed.service.model.OrderPurchaseProduct;

@Service("orderPurchaseProductService")
public class OrderPurchaseProductServiceImpl implements OrderPurchaseProductService {

	@Autowired
	OrderPurchaseProductDao orderPurchaseProductDao;
	
	@Override
	public List<OrderPurchaseProduct> findAll(OrderPurchaseProduct orderPurchaseProduct) {
		// TODO Auto-generated method stub
		return orderPurchaseProductDao.findAllOrderPurchaseProductByID(orderPurchaseProduct);
	}

	@Override
	public List<OrderPurchaseProduct> findById(OrderPurchaseProduct orderPurchaseProduct) {
		// TODO Auto-generated method stub
		return orderPurchaseProductDao.findOrderPurchaseProductByID(orderPurchaseProduct);
	}

	@Override
	public void save(OrderPurchaseProduct orderPurchaseProduct) {
		orderPurchaseProductDao.saveOrderPurchaseProduct(orderPurchaseProduct);
	}

	@Override
	public void delete(OrderPurchaseProduct orderPurchaseProduct) {
		orderPurchaseProductDao.deleteOrderPurchaseProduct(orderPurchaseProduct);
	}

	@Override
	public void update(OrderPurchaseProduct orderPurchaseProduct) {
		orderPurchaseProductDao.updateOrderPurchaseProduct(orderPurchaseProduct);
	}

	public OrderPurchaseProductDao getOrderPurchaseProductDao() {
		return orderPurchaseProductDao;
	}

	public void setOrderPurchaseProductDao(OrderPurchaseProductDao orderPurchaseProductDao) {
		this.orderPurchaseProductDao = orderPurchaseProductDao;
	}
	
}
