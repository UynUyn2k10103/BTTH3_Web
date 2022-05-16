package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entites.Product;
import com.repository.ProductRepository;
import com.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	ProductRepository productRepo;
	
	@Override
	public void addProduct(Product product) {
		productRepo.save(product);
	}
	
	@Override
	public Product getProductById(Long id) {
		return productRepo.findById(id).orElse(null);
	}
	
	
	@Override
	public List<Product> getListProduct(){
		return productRepo.findAll();
	}
	
	@Override
	public void updateProduct(Product product) {
		Product editProduct = this.getProductById(product.getId());
		editProduct.setDescription(product.getDescription());
		editProduct.setPrice(product.getPrice());
		productRepo.save(editProduct);
	}
	
	@Override
	public void deleteProduct (Product product) {
		productRepo.delete(product);
	}
	@Override
	public boolean getProductByCode(String code) {
		Product product = productRepo.getProductByCode(code);
		if (product == null) return true;
		return false;
	}
}
