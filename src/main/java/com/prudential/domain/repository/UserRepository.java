package com.prudential.domain.repository;

import java.util.Optional;

import com.prudential.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The JPA repository for {@link User} entities.
 * 
 * @author Hu Cai
 */
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * find user with email
	 */
	Optional<User> findByEmail(String email);
}
