package com.prudential.service;


import com.prudential.exception.EntityNotFoundException;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.prudential.domain.entity.User;
import com.prudential.domain.service.UserDomainService;
import com.prudential.dto.UserDTO;
import com.prudential.dto.LoginDTO;
import com.prudential.exception.EntityAlreadyExistsException;

import java.util.Optional;

/**
 * 
 * @author Hu Cai
 */
@Service
public class UserServiceImpl implements UserService {

	private final UserDomainService userDomainService;

	private final ConversionService conversionService;

	public UserServiceImpl(UserDomainService userDomainService, ConversionService conversionService) {
		this.userDomainService = userDomainService;
		this.conversionService = conversionService;
	}

	@Override
	public UserDTO registerUser(UserDTO userDTO) throws EntityAlreadyExistsException {
		if (userDomainService.getByEmail(userDTO.getEmail()).isPresent()) {
			throw new EntityAlreadyExistsException("User Already Exists!");
		}
		User newUser = userDomainService.createOrUpdate(new User(userDTO.getName(), userDTO.getEmail()));
		return conversionService.convert(newUser, UserDTO.class);
	}

	@Override
	public LoginDTO login(UserDTO userDTO) throws EntityNotFoundException {
		Optional<User> user = userDomainService.getByEmail(userDTO.getEmail());
		if (!user.isPresent()) {
			throw new EntityNotFoundException("User Not Found!");
		}
		return conversionService.convert(user.get(), LoginDTO.class);
	}

}
