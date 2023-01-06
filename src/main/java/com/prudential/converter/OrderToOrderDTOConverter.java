package com.prudential.converter;

import java.time.format.DateTimeFormatter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.prudential.domain.entity.Order;
import com.prudential.dto.OrderDTO;
import com.prudential.dto.CarDTO;
import com.prudential.dto.UserDTO;

@Component
public class OrderToOrderDTOConverter implements Converter<Order, OrderDTO> {

	@Override
	public OrderDTO convert(Order order) {

		return OrderDTO.builder()
				.id(order.getId())
				.created(order.getCreated().format(DateTimeFormatter.ISO_DATE_TIME))
				.beginning(order.getBeginning().format(DateTimeFormatter.ISO_DATE))
				.end(order.getEnd().format(DateTimeFormatter.ISO_DATE))
				.user(UserDTO.builder()
						.id(order.getUser().getId())
						.name(order.getUser().getName())
						.email(order.getUser().getEmail())
						.build())
				.car(CarDTO.builder()
						.id(order.getCar().getId())
						.plateNumber(order.getCar().getPlateNumber())
						.model(order.getCar().getModel())
						.build())
				.build();
	}

}
