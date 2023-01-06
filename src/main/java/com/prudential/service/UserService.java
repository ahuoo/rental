package com.prudential.service;

import com.prudential.dto.LoginDTO;
import com.prudential.dto.UserDTO;
import com.prudential.exception.EntityAlreadyExistsException;
import com.prudential.exception.EntityNotFoundException;

/**
 * Service that handles user related calls commands using domain services
 * 
 * @author Hu Cai
 */
public interface UserService {
	
	UserDTO registerUser(UserDTO userDTO) throws EntityAlreadyExistsException;

    LoginDTO login(UserDTO userDTO) throws EntityNotFoundException;
}
