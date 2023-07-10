package com.nnk.springboot.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "bidlist")
public class BidList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer BidListId;

	@NotBlank(message = "Account is mandatory")
	private String account;

	@NotBlank(message = "Type is mandatory")
	private String type;

	@Positive(message = "Must be a positive number")
	private Double bidQuantity;
	
	private Double askQuantity;
	private Double bid;
	private Double ask;
	private String benchmark;
	private LocalDate bidListDate;
	private String commentary;
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
