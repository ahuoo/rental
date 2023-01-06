package com.prudential.service;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.prudential.dto.CarDTO;
import com.prudential.dto.UpdateCarDTO;
import com.prudential.exception.DateFormatNotValidException;
import com.prudential.exception.DatesNotValidException;
import com.prudential.exception.EntityAlreadyExistsException;
import com.prudential.exception.EntityNotFoundException;

/**
 * Service that handles car related calls using domain services
 * 
 * @author Hu Cai
 */
public interface CarService {

	CarDTO registerCar(@NotNull CarDTO carDTO) throws EntityAlreadyExistsException;

	CarDTO updateCar(@NotNull Long carId, @NotNull UpdateCarDTO updateCarDTO)
			throws EntityNotFoundException, DatesNotValidException,  DateFormatNotValidException;

	List<CarDTO> searchCars(@NotNull String rentFrom, @NotNull String rentTo, @NotNull BigDecimal maxPricePerDay,
			@NotNull Integer page, @NotNull Integer pageSize) throws DatesNotValidException, DateFormatNotValidException;

}
