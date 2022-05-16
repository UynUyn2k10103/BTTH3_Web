package com.service;

import java.util.List;

import com.entites.Product;

public interface ProductService {
	void addProduct(Product product);
	Product getProductById(Long id);
	List<Product> getListProduct();
	void updateProduct(Product product);
	void deleteProduct (Product product);
	boolean getProductByCode(String code);
}
