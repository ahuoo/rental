package com.prudential.controller;

import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.client.utils.URIBuilder;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.prudential.domain.repository.OrderRepository;
import com.prudential.domain.repository.CarRepository;
import com.prudential.domain.repository.UserRepository;
import com.prudential.domain.service.OrderDomainService;
import com.prudential.domain.service.CarDomainService;
import com.prudential.domain.service.UserDomainService;
import com.prudential.dto.OrderDTO;
import com.prudential.dto.CarDTO;
import com.prudential.dto.CreateOrderDTO;
import com.prudential.dto.UpdateCarDTO;
import com.prudential.dto.UserDTO;

/**
 * 
 * @author Hu Cai
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class CarRentalBase {
	
	private final String USERS_PATH = "/users";
	private final String CARS_PATH = "/cars"; 
	private final String BOOKINGS_PATH = "/orders";

	private RestTemplate restTemplate = new RestTemplate();

	@LocalServerPort
	private int port;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	protected UserDomainService userDomainService;
	
	@Autowired
	protected CarDomainService carDomainService;
	
	@Autowired
	protected OrderDomainService orderDomainService;

	@Before
	public void clean() {
		orderRepository.deleteAll();
		userRepository.deleteAll();
		carRepository.deleteAll();
	}

	protected long registerUser(String name, String email) {
		UserDTO userDTO = UserDTO.builder()
				.name(name)
				.email(email)
				.build();
		UserDTO createdUser = performCall(HttpMethod.POST, userDTO, USERS_PATH, UserDTO.class);
		return createdUser.getId();
	}
	
	protected long registerCar(String plateNumber, String model) {
		CarDTO carDTO = CarDTO.builder()
				.plateNumber(plateNumber)
				.model(model)
				.build();
		CarDTO createdCar = performCall(HttpMethod.POST, carDTO, CARS_PATH, CarDTO.class);
		return createdCar.getId();
	}
	
	protected void updateCar(long carId, BigDecimal pricePerDay) {
		UpdateCarDTO updateCarDTO = UpdateCarDTO.builder()
				.pricePerDay(pricePerDay)
				.build();
		performCall(HttpMethod.PUT, updateCarDTO, CARS_PATH + "/" + carId, CarDTO.class);
	}
	
	@SuppressWarnings("unchecked")
	protected List<CarDTO> searchCars(String rentFrom, String rentTo, String maxPricePerDay, String page,
			String pageSize) throws URISyntaxException {
		
		URIBuilder uriBuilder = new URIBuilder(CARS_PATH);
		uriBuilder.addParameter("rentFrom", rentFrom);
		uriBuilder.addParameter("rentTo", rentTo);
		uriBuilder.addParameter("maxPricePerDay", maxPricePerDay);
		uriBuilder.addParameter("page", page);
		uriBuilder.addParameter("pageSize", pageSize);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return performCall(HttpMethod.GET, headers, uriBuilder.build().toString(), List.class);
	}
	
	protected long createOrder(long userId, Long carId, String beginning, String end) {
		CreateOrderDTO createOrderDTO = CreateOrderDTO.builder()
				.userId(userId)
				.carId(carId)
				.beginning(beginning)
				.end(end)
				.build();
		OrderDTO createdBooking = performCall(HttpMethod.POST, createOrderDTO, BOOKINGS_PATH, OrderDTO.class);
		return createdBooking.getId();
	}

	protected <I, O> O performCall(HttpMethod httpMethod, I input, String path, Class<O> response) {
		HttpEntity<I> httpEntity = new HttpEntity<>(input);
		ResponseEntity<O> responseEntity = restTemplate.exchange("http://localhost:" + port + path, httpMethod,
				httpEntity, response);
		return responseEntity.getBody();
	}

}
