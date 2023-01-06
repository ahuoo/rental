package com.prudential.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Order data transfer object used to pass create order data to end
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
public class CreateOrderDTO {
	
	@NotNull(message = "User can not be null!")
	private Long userId;
	
	@NotNull(message = "Car can not be null!")
	private Long carId;
	
	@NotNull(message = "Date from can not be null!")
	private String beginning;

	@NotNull(message = "Date to can not be null!")
	private String end;

}
