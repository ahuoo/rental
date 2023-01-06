package com.prudential.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Car data transfer object used to return/pass car data from/to end points.
 * 
 * @author Hu Cai
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDTO {
	
	@ApiModelProperty(readOnly = true)
	private Long id;
	
	@ApiModelProperty(readOnly = true)
	private String created;
	
	@NotBlank(message = "Plate number can not be blank or null!")
	private String plateNumber;
	
	@NotBlank(message = "Car model can not be blank or null!")
	private String model;

	@ApiModelProperty(readOnly = true)
	private BigDecimal pricePerDay;

	@ApiModelProperty(readOnly = true)
	private LinkedHashMap<LocalDate, Boolean> availableDateList;

}
