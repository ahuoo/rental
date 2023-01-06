package com.prudential.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.prudential.domain.entity.Order;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.prudential.domain.entity.Car;
import com.prudential.domain.service.CarDomainService;
import com.prudential.dto.CarDTO;
import com.prudential.dto.UpdateCarDTO;
import com.prudential.exception.DateFormatNotValidException;
import com.prudential.exception.DatesNotValidException;
import com.prudential.exception.EntityAlreadyExistsException;
import com.prudential.exception.EntityNotFoundException;
import com.prudential.util.Utilities;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Hu Cai
 */
@Slf4j
@Service
public class CarServiceImpl implements CarService {

	private final CarDomainService carDomainService;

	private final ConversionService conversionService;

	public CarServiceImpl(CarDomainService carDomainService, ConversionService conversionService) {
		this.carDomainService = carDomainService;
		this.conversionService = conversionService;
	}

	@Override
	public CarDTO registerCar(@NotNull CarDTO carDTO) throws EntityAlreadyExistsException {
		if (carDomainService.getByPlateNumber(carDTO.getPlateNumber()).isPresent()) {
			throw new EntityAlreadyExistsException("Car Already Exists!");
		}
		Car car = carDomainService.createOrUpdate(new Car(carDTO.getPlateNumber(), carDTO.getModel()));
		return conversionService.convert(car, CarDTO.class);
	}

	@Override
	public CarDTO updateCar(@NotNull Long carId, UpdateCarDTO updateCarDTO)
			throws EntityNotFoundException {
		Car car = carDomainService.getById(carId);
		car.setPricePerDay(updateCarDTO.getPricePerDay());
		return conversionService.convert(carDomainService.createOrUpdate(car), CarDTO.class);
	}

	@Override
	public List<CarDTO> searchCars(@NotNull String rentFrom, @NotNull String rentTo,
			@NotNull BigDecimal maxPricePerDay, @NotNull Integer page, @NotNull Integer pageSize)
			throws DatesNotValidException, DateFormatNotValidException {
		LocalDate rentFromDate = Utilities.toLocalDate(rentFrom);
		LocalDate rentToDate = Utilities.toLocalDate(rentTo);
		
		if (!Utilities.isValidDates(rentFromDate, rentToDate)) {
			log.debug("Provided dates are not valid! date from: {}, date to: {}", rentFromDate, rentToDate);
			throw new DatesNotValidException("Provided dates are not valid!");
		}

		List<Car> carList = carDomainService.findCarsByMaxPrice(maxPricePerDay, page, pageSize);
		ArrayList<CarDTO> carDTOList = new ArrayList<>();
		List<LocalDate> dateList = Utilities.getListDateByRange(rentFromDate, rentToDate);
		for(Car car : carList){
			//date->isAvailableForRent
			LinkedHashMap<LocalDate, Boolean> availableDateMap = new LinkedHashMap<>();
			dateList.stream().forEach(date -> availableDateMap.put(date, true));
			List<Order> orders = car.getOrders();
			orders.stream().forEach(order -> {
				Utilities.getListDateByRange(order.getBeginning(), order.getEnd()).stream().forEach(date -> {
					if(Utilities.isDateTimeWithinPeriod(date, rentFromDate, rentToDate)){
						availableDateMap.put(date, false);
					}
				});
			});
			CarDTO carDTO = conversionService.convert(car, CarDTO.class);
			carDTO.setAvailableDateList(availableDateMap);
			carDTOList.add(carDTO);
		}

		return carDTOList;
	}
}
