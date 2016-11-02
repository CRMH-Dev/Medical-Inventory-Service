package org.tallymed.service.dao;

import java.util.List;

import org.tallymed.service.model.OrderPurchaseProduct;

public interface OrderPurchaseProductDao {
	public void saveOrderPurchaseProduct (OrderPurchaseProduct orderPurchaseProduct);
	public void deleteOrderPurchaseProduct (OrderPurchaseProduct orderPurchaseProduct);
	public void updateOrderPurchaseProduct (OrderPurchaseProduct orderPurchaseProduct);
	public List<OrderPurchaseProduct> findOrderPurchaseProductByID(OrderPurchaseProduct orderPurchaseProduct);
	public List<OrderPurchaseProduct> findAllOrderPurchaseProductByID(OrderPurchaseProduct orderPurchaseProduct);
}
