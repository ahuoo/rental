package com.prudential.domain.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import com.prudential.domain.entity.Car;
import com.prudential.exception.EntityNotFoundException;
/**
 * The service that manage {@link Car} entities
 * 
 * @author Hu Cai
 */
public interface CarDomainService {


	Car createOrUpdate(@NotNull Car car);

	Car getById(@NotNull Long id) throws EntityNotFoundException;

	Optional<Car> getByPlateNumber(@NotNull String plateNumber);

	List<Car> findCarsByMaxPrice(BigDecimal maxPricePerDay, int page, int pageSize);



}
