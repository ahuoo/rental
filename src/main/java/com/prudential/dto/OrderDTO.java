package com.prudential.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Order data transfer object used to return order data from end
 * points.
 * 
 * @author Hu Cai
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {
	
	@NotNull
	private Long id;
	
	@NotNull
	private String created;

	@NotNull(message = "Date from can not be null!")
	private String beginning;

	@NotNull(message = "Date to can not be null!")
	private String end;
	
	@NotNull(message = "User can not be null!")
	private UserDTO user;
	
	@NotNull(message = "Car can not be null!")
	private CarDTO car;
}
