package com.prudential.controller;

import com.prudential.dto.CarDTO;
import com.prudential.dto.UpdateCarDTO;
import com.prudential.exception.DateFormatNotValidException;
import com.prudential.exception.DatesNotValidException;
import com.prudential.exception.EntityAlreadyExistsException;
import com.prudential.exception.EntityNotFoundException;
import com.prudential.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

/**
 * All operations with health will be routed by this controller.
 * 
 */
@Slf4j
@RestController
@RequestMapping("/health")
public class HealthController {

	@GetMapping
	public String health() {
		log.debug("Called HealthController.health  for health check");
		return "hey, I am healthy";
	}

}
