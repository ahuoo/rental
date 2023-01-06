package com.prudential.domain.service;


import java.util.Optional;

import javax.validation.constraints.NotNull;

import com.prudential.domain.entity.User;
import com.prudential.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prudential.exception.EntityNotFoundException;

/**
 * The service that manage {@link User} entities
 * 
 * @author Hu Cai
 */
@Service
public class UserDomainServiceImpl implements UserDomainService {

	private final UserRepository userRepository;

	public UserDomainServiceImpl(UserRepository repository) {
		this.userRepository = repository;
	}


	@Transactional
	@Override
	public User createOrUpdate(User user) {
		return userRepository.saveAndFlush(user);
	}


	@Override
	public User getById(Long id) throws EntityNotFoundException {
		return userRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("User not found ID: " + id));
	}

	@Override
	public Optional<User> getByEmail(@NotNull String email) {
		return userRepository.findByEmail(email);
	}
}
