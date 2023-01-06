package com.prudential.controller;

import javax.validation.Valid;

import com.prudential.dto.LoginDTO;
import com.prudential.exception.EntityNotFoundException;
import com.prudential.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.prudential.dto.UserDTO;
import com.prudential.exception.EntityAlreadyExistsException;

import lombok.extern.slf4j.Slf4j;

/**
 * All operations with a user will be routed by this controller.
 * 
 */
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserDTO registerUser(@Valid @RequestBody UserDTO userDTO) throws EntityAlreadyExistsException {

		log.debug("Called UserController.registerUser with new user :{}", userDTO);

		return userService.registerUser(userDTO);
	}

	@PostMapping("/login")
	@ResponseStatus(HttpStatus.CREATED)
	public LoginDTO login(@Valid @RequestBody UserDTO userDTO) throws EntityNotFoundException {

		log.debug("Called UserController.login with user :{}", userDTO);

		return userService.login(userDTO);
	}

}
