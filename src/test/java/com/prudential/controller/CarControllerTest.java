package com.prudential.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.Test;

import com.prudential.domain.entity.Car;
import com.prudential.dto.CarDTO;
import com.prudential.exception.EntityNotFoundException;

/**
 * 
 * @author Hu Cai
 *
 */
public class CarControllerTest extends CarRentalBase {
	
	@Test
    public void canRegisterCar() throws EntityNotFoundException {
		String PLATE_NUMBER = "C100000";
		String MODEL = "BYD";
		long carId = registerCar(PLATE_NUMBER, MODEL);
		// get user from db
		Car car = carDomainService.getById(carId);
		assertEquals(PLATE_NUMBER, car.getPlateNumber());
		assertEquals(MODEL, car.getModel());
	}
	
	@Test
    public void registerCarWithPlateNumberAlreadyRegistered(){
		String PLATE_NUMBER = "B100000";
		String MODEL = "BYD";
		registerCar(PLATE_NUMBER, MODEL);
		try {
			registerCar(PLATE_NUMBER, "FORD");
			fail();
		}catch (Exception e) {
			assertTrue(e.getMessage().contains("Entity Already Exists"));
		}
	}

	@Test
    public void createMultipleCarsAndSearchByPage() throws URISyntaxException {
		for (int i = 0; i < 6; i++) {
			long carId = registerCar("ABC23" + i, "HONDA");
			// update price
			updateCar(carId,  BigDecimal.valueOf(100));
		}
		// page 0 size 5
		List<CarDTO> availableCarsPage1 = searchCars("2023-01-10", "2023-01-15", "100", "0", "5");
		assertNotNull(availableCarsPage1);
		assertEquals(5, availableCarsPage1.size());
		// page 1 size 5
		List<CarDTO> availableCarsPage2 = searchCars("2023-01-10", "2023-01-15", "100", "1", "5");
		assertNotNull(availableCarsPage2);
		assertEquals(1, availableCarsPage2.size());
		// page 0 size 10
		List<CarDTO> availableCarsPage1Size10 = searchCars("2023-01-10", "2023-01-15","100", "0", "10");
		assertNotNull(availableCarsPage1Size10);
		assertEquals(6, availableCarsPage1Size10.size());
	}
	

}
