package com.nnk.springboot.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
@Table(name = "rating")
public class Rating {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "moodysRating")
	@NotBlank(message = "Moody's rating is mandatory")
	private String moodysRating;

	@Column(name = "sandPRating")
	@NotBlank(message = "S and P's rating is mandatory")
	private String sandPRating;

	@Column(name = "fitchRating")
	@NotBlank(message = "Fitch's rating is mandatory")
	private String fitchRating;

	@Column(name = "orderNumber")
	private Integer orderNumber;

	public Rating() {
		// Empty constructor is used by JPA to create entities
	}

	public Rating(String moodysRating, String sandpRating, String fitchRating, Integer orderNumber) {
		this.moodysRating = moodysRating;
		this.sandPRating = sandpRating;
		this.fitchRating = fitchRating;
		this.orderNumber = orderNumber;
	}

}
