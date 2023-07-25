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
@Table(name = "rulename")
public class RuleName {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	@NotBlank(message = "Name is mandatory")
	private String name;

	@Column(name = "description")
	private String description;
	
	@Column(name = "json")
	private String json;
	
	@Column(name = "template")
	private String template;
	
	@Column(name = "sqlStr")
	private String sqlStr;
	
	@Column(name = "sqlPart")
	private String sqlPart;

	public RuleName() {
		// Empty constructor is used by JPA to create entities
	}

	public RuleName(String name, String description, String json, String template, String sqlStr, String sqlPart) {
		this.name = name;
		this.description = description;
		this.json = json;
		this.template = template;
		this.sqlStr = sqlStr;
		this.sqlPart = sqlPart;
	}

}
