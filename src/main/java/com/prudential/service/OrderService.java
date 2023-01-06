package com.prudential.service;

import javax.validation.constraints.NotNull;

import com.prudential.dto.OrderDTO;
import com.prudential.dto.CreateOrderDTO;
import com.prudential.exception.CarNotAvailableException;
import com.prudential.exception.DateFormatNotValidException;
import com.prudential.exception.DatesNotValidException;
import com.prudential.exception.EntityNotFoundException;

import java.util.List;

/**
 * Service that handles order related calls using domain services
 * 
 * @author Hu Cai
 */
public interface OrderService {

	OrderDTO createOrder(@NotNull CreateOrderDTO createOrderDTO) throws EntityNotFoundException,
			CarNotAvailableException, DatesNotValidException, DateFormatNotValidException;

	List<OrderDTO> getOrders(Long userId);
}
