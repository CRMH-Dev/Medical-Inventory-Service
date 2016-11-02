package org.tallymed.service.provider;

import java.util.List;

import org.tallymed.service.model.OrderPurchase;

public interface OrderPurchaseService {
	public List<OrderPurchase> findAll(OrderPurchase orderPurchase);
	public List<OrderPurchase> findById(OrderPurchase orderPurchase);
	public void save(OrderPurchase orderPurchase);
	public void delete(OrderPurchase orderPurchase);
	public void update(OrderPurchase orderPurchase);
}
