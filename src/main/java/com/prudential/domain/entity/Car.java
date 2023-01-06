package com.prudential.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * This entity represents a specific car that can be rented
 *
 */
@Entity
@Table(name = "t_car", uniqueConstraints = @UniqueConstraint(name = "uc_plate_number", columnNames = { "Plate_Number" }))
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class Car {

	@Id
	@Getter
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", insertable = false, updatable = false, nullable = false)
	private Long id;

	@Getter
	@NotNull
	@Column(name = "Created", nullable = false)
	private LocalDateTime created = LocalDateTime.now();

	@Getter
	@NotNull
	@NonNull
	@Length(min = 1, max = 255)
	@Column(name = "Plate_Number", nullable = false)
	private String plateNumber;

	@Getter
	@NotNull
	@NonNull
	@Length(min = 1, max = 100)
	@Column(name = "Model", nullable = false)
	private String model;

	@Getter
	@Setter
	@DecimalMin(value = "0", message = "The price per day can not be negative!")
	@Column(name = "Price_Per_Day")
	private BigDecimal pricePerDay;

	@Getter
    @OneToMany(mappedBy = "car", fetch = FetchType.LAZY)
    private List<Order> orders;

}
