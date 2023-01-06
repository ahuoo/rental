package com.prudential.domain.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.prudential.domain.entity.Car;
import com.prudential.domain.repository.CarRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prudential.exception.EntityNotFoundException;

/**
 * The service that manage {@link Car} entities
 * 
 * @author Hu Cai
 */
@Service
public class CarDomainServiceImpl implements CarDomainService {

	private final CarRepository carRepository;

	public CarDomainServiceImpl(CarRepository carRepository) {
		this.carRepository = carRepository;
	}


	@Override
	public Car createOrUpdate(@NotNull Car car) {
		return carRepository.saveAndFlush(car);
	}


	@Override
	public Car getById(@NotNull Long id) throws EntityNotFoundException {
		return carRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Car not found ID: " + id));
	}

	public Optional<Car> getByPlateNumber(@NotNull String plateNumber) {
		return carRepository.findByPlateNumber(plateNumber);
	}

	@Transactional
	@Override
	public List<Car> findCarsByMaxPrice(BigDecimal maxPricePerDay, int page, int pageSize) {

		return carRepository.findCarsByMaxPrice(maxPricePerDay, PageRequest.of(page, pageSize))
				.stream()
				.collect(Collectors.toList());
	}


}
