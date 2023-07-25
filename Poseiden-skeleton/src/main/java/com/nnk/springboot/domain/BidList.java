package com.nnk.springboot.domain;

import jakarta.persistence.Column;
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
	@Column(name = "BidListId")
	private Integer BidListId;
	
	@Column(name = "account")
	@NotBlank(message = "Account is mandatory")
	private String account;

	@Column(name = "type")
	@NotBlank(message = "Type is mandatory")
	private String type;

	@Column(name = "bidQuantity")
	@Positive(message = "Must be a positive number")
	private Double bidQuantity;
	
	@Column(name = "askQuantity")
	private Double askQuantity;
	
	@Column(name = "bid")
	private Double bid;
	
	@Column(name = "ask")
	private Double ask;
	
	@Column(name = "benchmark")
	private String benchmark;
	
	@Column(name = "bidListDate")
	private LocalDate bidListDate;
	
	@Column(name = "commentary")
	private String commentary;
	
	@Column(name = "security")
	private String security;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "trader")
	private String trader;
	
	@Column(name = "book")
	private String book;
	
	@Column(name = "creationName")
	private String creationName;
	
	@Column(name = "creationDate")
	private LocalDate creationDate;
	
	@Column(name = "revisionName")
	private String revisionName;
	
	@Column(name = "revisionDate")
	private LocalDate revisionDate;
	
	@Column(name = "dealName")
	private String dealName;
	
	@Column(name = "dealType")
	private String dealType;
	
	@Column(name = "sourceListId")
	private String sourceListId;
	
	@Column(name = "side")
	private String side;
	
	public BidList() {
        // Empty constructor is used by JPA to create entities
    }
	
	public BidList(String account, String type, Double bidQuantity ) {
        this.account = account;
        this.type = type;
        this.bidQuantity = bidQuantity;
    }

}
