package com.nnk.springboot.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "trade")
public class Trade {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tradeId")
	private Integer tradeId;

	@Column(name = "account")
	@NotBlank(message = "Account is mandatory")
	private String account;

	@Column(name = "type")
	@NotBlank(message = "Type is mandatory")
	private String type;

	@Column(name = "buyQuantity")
	@Positive(message = "Must be a positive number")
	private Double buyQuantity;

	@Column(name = "sellQuantity")
	private Double sellQuantity;
	
	@Column(name = "buyPrice")
	private Double buyPrice;
	
	@Column(name = "sellPrice")
	private Double sellPrice;
	
	@Column(name = "benchmark")
	private String benchmark;
	
	@Column(name = "tradeDate")
	private LocalDate tradeDate;
	
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
	
	public Trade() {
        // Empty constructor is used by JPA to create entities
    }

	/**
	 * Constructor created for the original test provided
	 * 
	 * @param account
	 * @param type
	 */
    public Trade(String account, String type) {
        this.account = account;
        this.type = type;
    }

	@Override
	public String toString() {
		return "Trade [tradeId=" + tradeId + ", account=" + account + ", type=" + type + ", buyQuantity=" + buyQuantity
				+ ", sellQuantity=" + sellQuantity + ", buyPrice=" + buyPrice + ", sellPrice=" + sellPrice
				+ ", benchmark=" + benchmark + ", tradeDate=" + tradeDate + ", security=" + security + ", status="
				+ status + ", trader=" + trader + ", book=" + book + ", creationName=" + creationName
				+ ", creationDate=" + creationDate + ", revisionName=" + revisionName + ", revisionDate=" + revisionDate
				+ ", dealName=" + dealName + ", dealType=" + dealType + ", sourceListId=" + sourceListId + ", side="
				+ side + "]";
	}
	
    
}
