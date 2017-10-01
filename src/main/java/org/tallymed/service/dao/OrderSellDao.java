package org.tallymed.service.dao;

import java.util.List;

import org.tallymed.service.model.OrderSell;

public interface OrderSellDao {
	public void saveOrderSell (OrderSell orderSell);
	public void deleteOrderSell (OrderSell orderSell);
	public void updateOrderSell (OrderSell orderSell);
	public List<OrderSell> findOrderSellByID(OrderSell orderSell);
}
