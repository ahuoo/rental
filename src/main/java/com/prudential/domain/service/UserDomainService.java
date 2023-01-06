package com.prudential.domain.service;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import com.prudential.domain.entity.User;
import com.prudential.exception.EntityNotFoundException;
/**
 * The service that manage {@link User} entities
 * 
 * @author Hu Cai
 */
public interface UserDomainService {


	User createOrUpdate(@NotNull User user);


	User getById(@NotNull Long id) throws EntityNotFoundException;
	

	Optional<User> getByEmail(@NotNull String email);


}
