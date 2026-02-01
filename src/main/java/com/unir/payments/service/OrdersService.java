package com.unir.payments.service;

import com.unir.payments.data.model.Order;
import com.unir.payments.controller.model.OrderRequest;
import java.util.List;

public interface OrdersService {
	
//	Order createOrder(OrderRequest request);
//
//	Order getOrder(String id);

	List<Order> getOrders();

}
