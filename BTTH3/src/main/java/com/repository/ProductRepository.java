package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.entites.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	@Query(value = "SELECT * FROM product WHERE p_code = ?", nativeQuery = true)
	Product getProductByCode(String code);
}
