package com.prudential.dto;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Car data transfer object
 * 
 * @author Hu Cai
 *
 */
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UpdateCarDTO {

	@NotNull(message = "Price per day can not be null!")
	@DecimalMin(value = "0", message = "The price per day can not be negative!")
	private BigDecimal pricePerDay;
}
