package com.nnk.springboot.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "curvepoint")
public class CurvePoint {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "curveId")
	@NotNull(message = "Must not be null")
	private Integer curveId;

	@Column(name = "asOfDate")
	private LocalDate asOfDate;
	
	@Column(name = "term")
	private Double term;
	
	@Column(name = "value")
	private Double value;
	
	@Column(name = "creationDate")
	private LocalDate creationDate;

	public CurvePoint() {
		// Empty constructor is used by JPA to create entities
	}

	public CurvePoint(Integer curveId, Double term, Double value) {
		this.curveId = curveId;
		this.term = term;
		this.value = value;
	}

}
