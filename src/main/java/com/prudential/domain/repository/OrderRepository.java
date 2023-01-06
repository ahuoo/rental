package com.prudential.domain.repository;

import com.prudential.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * The JPA repository for {@link Order} entities.
 * 
 * @author Hu Cai
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
    /**
     * Finds all available cars less than max rent per day
     */
    @Query(value="select o.* from t_order o "
            + "where o.car_id=:carId and ((:rentFrom between o.date_from and o.date_to) or (:rentTo between o.date_from and o.date_to) ) limit 1 "
            ,nativeQuery = true)
    Order findIntersectionOrder(@Param("rentFrom") LocalDate rentFrom, @Param("rentTo") LocalDate rentTo,@Param("carId") Long carId);

    @Query(value="select o.* from t_order o where user_id=:userId "
            ,nativeQuery = true)
    List<Order> findOrders( @Param("userId") Long userId);
}
