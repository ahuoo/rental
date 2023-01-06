package com.prudential.controller;

import javax.validation.Valid;

import com.prudential.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.prudential.dto.OrderDTO;
import com.prudential.dto.CreateOrderDTO;
import com.prudential.exception.CarNotAvailableException;
import com.prudential.exception.DateFormatNotValidException;
import com.prudential.exception.DatesNotValidException;
import com.prudential.exception.EntityNotFoundException;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * All operations with a order will be routed by this controller.
 * 
 */
@Slf4j
@RestController
@RequestMapping("/orders")
public class OrderController {

	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrderDTO createOrder(@Valid @RequestBody CreateOrderDTO createOrderDTO)
			throws EntityNotFoundException, CarNotAvailableException, DatesNotValidException,
			DateFormatNotValidException {

		log.debug("Called OrderController.createOrder with order :{}", createOrderDTO);

		return orderService.createOrder(createOrderDTO);
	}

	@GetMapping
	public List<OrderDTO> getOrders(@RequestParam Long userId){

		log.debug("Called OrderController.getOrders with parameters :{}", userId);

		return orderService.getOrders(userId);
	}
}
