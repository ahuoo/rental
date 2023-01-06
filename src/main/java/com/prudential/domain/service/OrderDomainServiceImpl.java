package com.prudential.domain.service;

import javax.validation.constraints.NotNull;

import com.prudential.domain.entity.Order;
import com.prudential.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prudential.exception.CarNotAvailableException;
import com.prudential.exception.EntityNotFoundException;

import java.util.List;

/**
 * The service that manage {@link Order} entities
 * 
 * @author Hu Cai
 */
@Service
public class OrderDomainServiceImpl implements OrderDomainService {

	private final OrderRepository orderRepository;

	public OrderDomainServiceImpl(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}


	@Transactional
	@Override
	public Order create(@NotNull Order order) throws CarNotAvailableException {
		Order intersectionOrder = orderRepository.findIntersectionOrder(order.getBeginning(), order.getEnd(), order.getCar().getId());
		if(intersectionOrder != null){
			throw  new CarNotAvailableException("Car was Ordered by others");
		}
		return orderRepository.saveAndFlush(order);
	}


	@Override
	public Order getById(@NotNull Long id) throws EntityNotFoundException {
		return orderRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Order not found ID: " + id));
	}

	@Override
	public List<Order> getOrders(Long userId) {
		List<Order> orderList = orderRepository.findOrders(userId);
		return orderList;
	}
}
