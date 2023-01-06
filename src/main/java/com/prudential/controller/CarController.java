package com.prudential.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import com.prudential.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.prudential.dto.CarDTO;
import com.prudential.dto.UpdateCarDTO;
import com.prudential.exception.DateFormatNotValidException;
import com.prudential.exception.DatesNotValidException;
import com.prudential.exception.EntityAlreadyExistsException;
import com.prudential.exception.EntityNotFoundException;

import lombok.extern.slf4j.Slf4j;

/**
 * All operations with a car will be routed by this controller.
 * 
 */
@Slf4j
@RestController
@RequestMapping("/cars")
public class CarController {

	private final CarService carService;

	public CarController(CarService carService) {
		this.carService = carService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CarDTO registerCar(@Valid @RequestBody CarDTO carDTO) throws EntityAlreadyExistsException {

		log.debug("Called CarController.registerOrUpdateCar with car :{}", carDTO);

		return carService.registerCar(carDTO);
	}

	@PutMapping("/{carId}")
	public CarDTO updateCar(@PathVariable long carId,@Valid @RequestBody UpdateCarDTO updateCarDTO)
			throws EntityNotFoundException, DatesNotValidException, DateFormatNotValidException {

		log.debug("Called CarController.updateCar car ID :{}", carId);

		return carService.updateCar(carId, updateCarDTO);
	}

	@GetMapping
	public List<CarDTO> searchCars(@RequestParam String rentFrom, @RequestParam String rentTo,
			@RequestParam BigDecimal maxPricePerDay, @RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "5") Integer pageSize)
			throws DatesNotValidException, DateFormatNotValidException {

		log.debug("Called CarController.searchCars with parameters :{}, {}, {}", rentFrom, rentTo, maxPricePerDay);

		return carService.searchCars(rentFrom, rentTo, maxPricePerDay, page, pageSize);
	}

}
