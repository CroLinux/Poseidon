package com.nnk.springboot.domain;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Data
@Table(name = "trade")
public class Trade {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tradeId;

	@NotBlank(message = "Account is mandatory")
	private String account;

	@NotBlank(message = "Type is mandatory")
	private String type;

	@Positive(message = "Must be a positive number")
	private Double buyQuantity;

	private Double sellQuantity;
	private Double buyPrice;
	private Double sellPrice;
	private String benchmark;
	private LocalDate tradeDate;
	private String security;
	private String status;
	private String trader;
	private String book;
	private String creationName;
	private LocalDate creationDate;
	private String revisionName;
	private LocalDate revisionDate;
	private String dealName;
	private String dealType;
	private String sourceListId;
	private String side;
	
}
