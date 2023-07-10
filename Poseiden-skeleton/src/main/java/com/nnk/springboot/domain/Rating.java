package com.nnk.springboot.domain;

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
	private Integer id;

	@NotBlank(message = "Moody's rating is mandatory")
	private String moodysRating;

	@NotBlank(message = "S and P's rating is mandatory")
	private String sandPRating;

	@NotBlank(message = "Fitch's rating is mandatory")
	private String fitchRating;

	private Integer orderNumber;
	
}
