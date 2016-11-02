package org.tallymed.service.dao;

import java.util.List;

import org.tallymed.service.model.OrderPurchase;

public interface OrderPurchaseDao {
	public void saveOrderPurchase (OrderPurchase orderPurchase);
	public void deleteOrderPurchase (OrderPurchase orderPurchase);
	public void updateOrderPurchase (OrderPurchase orderPurchase);
	public List<OrderPurchase> findOrderPurchaseByID(OrderPurchase orderPurchase);
}
