package com.entites;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ProductNotFormat {
	private String id;
	private String code;
	private String description;
	private String price;
}
