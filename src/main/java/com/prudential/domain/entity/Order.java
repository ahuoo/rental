package com.prudential.domain.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * This entity represents a specific order for user and car
 *
 */
@Entity
@Table(name = "t_order")
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class Order {

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
	@Column(name = "Date_From", nullable = false)
	private LocalDate beginning;

	@Getter
	@NotNull
	@NonNull
	@Column(name = "Date_To", nullable = false)
	private LocalDate end;

	@Getter
	@NotNull
	@NonNull
	@ManyToOne
	@JoinColumn(name = "User_Id", nullable = false)
	private User user;

	@Getter
	@NotNull
	@NonNull
	@ManyToOne
	@JoinColumn(name = "Car_Id", nullable = false)
	private Car car;
}
