package com.nnk.springboot.domain;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Data
@Table(name = "curvepoint")
public class CurvePoint {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Positive(message = "Must be a positive number")
	private Integer curveId;
	
	private LocalDate asOfDate;
	private Double term;
	private Double value;
	private LocalDate creationDate;
	
}
