package com.prudential.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.constraints.NotNull;

import com.prudential.domain.entity.Order;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.prudential.domain.entity.Car;
import com.prudential.domain.entity.User;
import com.prudential.domain.service.OrderDomainService;
import com.prudential.domain.service.CarDomainService;
import com.prudential.domain.service.UserDomainService;
import com.prudential.dto.OrderDTO;
import com.prudential.dto.CreateOrderDTO;
import com.prudential.exception.CarNotAvailableException;
import com.prudential.exception.DateFormatNotValidException;
import com.prudential.exception.DatesNotValidException;
import com.prudential.exception.EntityNotFoundException;
import com.prudential.util.Utilities;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Hu Cai
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

	private final OrderDomainService orderDomainService;

	private final UserDomainService userDomainService;

	private final CarDomainService carDomainService;

	private final ConversionService conversionService;

	public OrderServiceImpl(OrderDomainService orderDomainService, UserDomainService userDomainService,
							CarDomainService carDomainService, ConversionService conversionService) {
		this.orderDomainService = orderDomainService;
		this.userDomainService = userDomainService;
		this.carDomainService = carDomainService;
		this.conversionService = conversionService;
	}

	@Override
	public OrderDTO createOrder(@NotNull CreateOrderDTO createOrderDTO)
			throws EntityNotFoundException, CarNotAvailableException, DatesNotValidException, DateFormatNotValidException {

		LocalDate beginningDate = Utilities.toLocalDate(createOrderDTO.getBeginning());
		LocalDate endDate= Utilities.toLocalDate(createOrderDTO.getEnd());
		
		if(!Utilities.isValidDates(beginningDate, endDate)) {
			log.debug("Provided dates are not valid! date from: {}, date to: {}", beginningDate, endDate);
			throw new DatesNotValidException("Provided dates are not valid!");
		}
		
		// get user with the provided id
		User user = userDomainService.getById(createOrderDTO.getUserId());
		// get car with the provided id
		Car car = carDomainService.getById(createOrderDTO.getCarId());
		// create order for user and car
		Order order = orderDomainService.create(new Order(beginningDate, endDate, user, car));

		return conversionService.convert(order, OrderDTO.class);
	}

	@Override
	public List<OrderDTO> getOrders(Long userId) {
		List<Order> orders = orderDomainService.getOrders(userId);
		List<OrderDTO> orderDTOList = orders.stream().map(order -> conversionService.convert(order, OrderDTO.class)).collect(Collectors.toList());;
		return orderDTOList;
	}

}
