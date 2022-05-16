package com.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
//@RequiredArgsConstructor
@Entity(name = "product")
@NoArgsConstructor
@AllArgsConstructor

public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "p_id")
	private Long id;
//	@NotEmpty(message = "You must fill code in the blank!")
	@Column(name = "p_code")
	private String code = "";
	
//	@NotEmpty(message = "You must fill description in the blank!")
	@Column(name = "p_description")
	private String description = "";
	
	
	@Column(name = "p_price")
	private double price;
}
