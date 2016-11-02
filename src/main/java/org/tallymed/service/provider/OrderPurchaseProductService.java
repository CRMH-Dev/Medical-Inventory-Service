package org.tallymed.service.provider;

import java.util.List;

import org.tallymed.service.model.OrderPurchaseProduct;

public interface OrderPurchaseProductService {
	public List<OrderPurchaseProduct> findAll(OrderPurchaseProduct orderPurchaseProduct);
	public List<OrderPurchaseProduct> findById(OrderPurchaseProduct orderPurchaseProduct);
	public void save(OrderPurchaseProduct orderPurchaseProduct);
	public void delete(OrderPurchaseProduct orderPurchaseProduct);
	public void update(OrderPurchaseProduct orderPurchaseProduct);
}
