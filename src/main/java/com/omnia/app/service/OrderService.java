package com.omnia.app.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.omnia.app.model.Order;
@Validated
public interface OrderService {
	
//	public Iterable<Order> getAllOrders();
//	public Order create(Order order);
//	public void update(Order order);
	
	   @NotNull Iterable<Order> getAllOrders();

	    Order create(@NotNull(message = "The order cannot be null.") @Valid Order order);

	    void update(@NotNull(message = "The order cannot be null.") @Valid Order order);
	

}
