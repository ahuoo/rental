package com.prudential.domain.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import com.prudential.domain.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * The JPA repository for {@link Car} entities.
 * 
 * @author Hu Cai
 */
public interface CarRepository extends JpaRepository<Car, Long> {

	/**
	 * Finds all available cars less than max rent per day
	 */
	@Query("select c from Car c "
			+ "where c.pricePerDay is not null and c.pricePerDay <= :maxPricePerDay "
			+ "order by c.pricePerDay desc")
	Page<Car> findCarsByMaxPrice(BigDecimal maxPricePerDay, Pageable pageable);
	
	/**
	 * find car with plate number
	 */
	Optional<Car> findByPlateNumber(String plateNumber);

}
