package com.prudential.converter;

import java.time.format.DateTimeFormatter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.prudential.domain.entity.Car;
import com.prudential.dto.CarDTO;
import com.prudential.dto.CarDTO.CarDTOBuilder;

@Component
public class CarToCarDTOConverter implements Converter<Car, CarDTO> {

	@Override
	public CarDTO convert(Car car) {

		CarDTOBuilder builder = CarDTO.builder()
				.id(car.getId())
				.created(car.getCreated().format(DateTimeFormatter.ISO_DATE_TIME))
				.plateNumber(car.getPlateNumber())
				.model(car.getModel())
				.pricePerDay(car.getPricePerDay());

		return builder.build();
	}

}
