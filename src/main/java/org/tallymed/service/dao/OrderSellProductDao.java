package org.tallymed.service.dao;

import java.util.List;

import org.tallymed.service.model.OrderSellProduct;

public interface OrderSellProductDao {
	public void saveOrderSellProduct (OrderSellProduct orderSellProduct);
	public void deleteOrderSellProduct (OrderSellProduct orderSellProduct);
	public void updateOrderSellProduct (OrderSellProduct orderSellProduct);
	public List<OrderSellProduct> findOrderSellProductByID(OrderSellProduct orderSellProduct);
	public List<OrderSellProduct> findAllOrderSellProductByID(OrderSellProduct orderSellProduct);
}
