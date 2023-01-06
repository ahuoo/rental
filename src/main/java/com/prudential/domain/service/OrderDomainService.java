package com.prudential.domain.service;

import javax.validation.constraints.NotNull;

import com.prudential.domain.entity.Order;
import com.prudential.exception.CarNotAvailableException;
import com.prudential.exception.EntityNotFoundException;

import java.util.List;

/**
 * The service that manage {@link Order} entities
 * 
 * @author Hu Cai
 */
public interface OrderDomainService {
	

	Order create(@NotNull Order order) throws CarNotAvailableException;

	Order getById(@NotNull Long id) throws EntityNotFoundException;

	List<Order> getOrders(Long userId);

}
