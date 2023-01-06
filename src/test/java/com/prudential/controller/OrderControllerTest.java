package com.prudential.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import com.prudential.domain.entity.Order;
import com.prudential.domain.entity.Car;
import com.prudential.exception.EntityNotFoundException;
import org.junit.Test;

/**
 * 
 * @author Hu Cai
 *
 */
public class OrderControllerTest extends CarRentalBase {

	@Test
	public void canCreateOrder() throws EntityNotFoundException {
		// user
		String NAME = "Tiger";
		String EMAIL = "tiger@gmail.com";
		// car
		String PLATE_NUMBER = "A888888";
		String MODEL = "Toyota Camry";

		BigDecimal PRICE_PER_DAY = BigDecimal.valueOf(100);
		// order
		String ORDER_BEGINNING = "2023-01-10";
		String ORDER_END = "2023-01-15";

		// register user
		long userId = registerUser(NAME, EMAIL);
		// register car
		long carId = registerCar(PLATE_NUMBER, MODEL);
		// update car price
		updateCar(carId,  PRICE_PER_DAY);
		// create order
		long bookingId = createOrder(userId, carId, ORDER_BEGINNING, ORDER_END);

		Order order = orderDomainService.getById(bookingId);

		assertEquals(userId, order.getUser().getId());
		assertEquals(carId, order.getCar().getId());
		assertEquals(ORDER_BEGINNING, order.getBeginning().toString());
		assertEquals(ORDER_END, order.getEnd().toString());
	}


	@Test
	public void canCreateAnotherOrderNotIntersectsWithTheFirstOrder() throws EntityNotFoundException {
		// user
		String NAME = "Tiger";
		String EMAIL = "tiger@gmail.com";
		// car
		String PLATE_NUMBER = "A888888";
		String MODEL = "Toyota Camry";
		// price
		BigDecimal PRICE_PER_DAY = BigDecimal.valueOf(80);
		// bookings
		String FIRST_ORDER_BEGINNING = "2023-01-10";
		String FIRST_ORDER_END = "2023-01-14";
		String SECOND_ORDER_BEGINNING = "2023-01-16";
		String SECOND_ORDER_END = "2023-01-18";


		// register user
		long userId = registerUser(NAME, EMAIL);
		// register car
		long carId = registerCar(PLATE_NUMBER, MODEL);
		// update car price
		updateCar(carId, PRICE_PER_DAY);
		// create order
		long firstOrderId = createOrder(userId, carId, FIRST_ORDER_BEGINNING, FIRST_ORDER_END);
		Order firstOrder = orderDomainService.getById(firstOrderId);
		assertEquals(userId, firstOrder.getUser().getId());
		assertEquals(carId, firstOrder.getCar().getId());
		assertEquals(FIRST_ORDER_BEGINNING, firstOrder.getBeginning().toString());
		assertEquals(FIRST_ORDER_END, firstOrder.getEnd().toString());

		// create another order
		long secondOrderId = createOrder(userId, carId, SECOND_ORDER_BEGINNING, SECOND_ORDER_END);
		Order secondOrder = orderDomainService.getById(secondOrderId);
		assertEquals(userId, secondOrder.getUser().getId());
		assertEquals(carId, secondOrder.getCar().getId());
		assertEquals(SECOND_ORDER_BEGINNING, secondOrder.getBeginning().toString());
		assertEquals(SECOND_ORDER_END, secondOrder.getEnd().toString());
	}

	@Test
	public void createOrderForCarThatHasCurrentOrderSamePeriod() throws EntityNotFoundException {
		// user
		String NAME = "Tiger";
		String EMAIL = "tiger@gmail.com";
		// car
		String PLATE_NUMBER = "A888888";
		String MODEL = "Toyota Camry";

		BigDecimal PRICE_PER_DAY = BigDecimal.valueOf(100);
		// order
		String FIRST_ORDER_BEGINNING = "2023-01-10";
		String FIRST_ORDER_END = "2023-01-14";
		String SECOND_ORDER_BEGINNING = "2023-01-13";
		String SECOND_ORDER_END = "2023-01-18";

		// register user
		long userId = registerUser(NAME, EMAIL);
		// register car
		long carId = registerCar(PLATE_NUMBER, MODEL);
		// update car price
		updateCar(carId,  PRICE_PER_DAY);
		// create order
		long bookingId = createOrder(userId, carId, FIRST_ORDER_BEGINNING, FIRST_ORDER_END);

		Order order = orderDomainService.getById(bookingId);

		assertEquals(userId, order.getUser().getId());
		assertEquals(carId, order.getCar().getId());
		assertEquals(FIRST_ORDER_BEGINNING, order.getBeginning().toString());
		assertEquals(FIRST_ORDER_END, order.getEnd().toString());

		// create another order intersects with the first one
		try {
			// create order
			createOrder(userId, carId, SECOND_ORDER_BEGINNING, SECOND_ORDER_END);
			fail();
		} catch (Exception e) {
			assertTrue(e.getMessage().contains("Car Not available"));
		}
	}

}
