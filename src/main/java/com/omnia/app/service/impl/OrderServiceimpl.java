package com.omnia.app.service.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.omnia.app.model.Order;
import com.omnia.app.repository.OrderRepository;
import com.omnia.app.service.OrderService;


@Service
@Transactional
public class OrderServiceimpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	

	@Override
	public Iterable<Order> getAllOrders() {
		// TODO Auto-generated method stub
		return orderRepository.findAll();
	}

	@Override
	public Order create(Order order) {
		 order.setDateCreated(LocalDate.now());
	        return this.orderRepository.save(order);
	}

	@Override
	public void update(Order order) {
		orderRepository.save(order);
		
	}

}
