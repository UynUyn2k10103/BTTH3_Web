package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.entites.Product;
import com.entites.ProductNotFormat;
import com.service.impl.ProductServiceImpl;

@Controller
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private ProductServiceImpl productServiceImpl;
	
	@GetMapping("")
	public String listProducts (Model model) {
		model.addAttribute("products", productServiceImpl.getListProduct());
		return "products";
	}
	
	//delete

	@GetMapping("/deleteProductById/{id}")
	public String deleteProductGet (Model model, @PathVariable(name = "id") Long id) {
		
		Product product = productServiceImpl.getProductById(id);
		if (product == null) {
			model.addAttribute("notification", "Can't found element by id = " + id + "!");
			model.addAttribute("products", productServiceImpl.getListProduct());
			return "products";
		}
		model.addAttribute("product", product);
		return "deleteProduct";
	}
	
	@PostMapping("/deleteProductById/{id}")
	public String deleteProductPost (HttpServletRequest request , Model model, @PathVariable(name = "id") Long id) {
		
		String choose = request.getParameter("choose");
		if (choose.equals("yes")){
			model.addAttribute("notification", "Delete Succesfully!");
			productServiceImpl.deleteProduct(productServiceImpl.getProductById(id));
		}
		
		
		
		model.addAttribute("products", productServiceImpl.getListProduct());

		return "products";
	}
	
	
	// edit
	@GetMapping("/editProductById/{id}")
	public String editProductByIdGet (Model model, @PathVariable(name = "id") Long id) {
		
		Product product = productServiceImpl.getProductById(id);
		if (product == null) {
			model.addAttribute("notification", "Can't found element by id = " + id + "!");
			model.addAttribute("products", productServiceImpl.getListProduct());
			return "products";
		}
		model.addAttribute("product", product);
		return "editProductById";
	}
	
	@GetMapping("/editProduct/{id}")
	public String editProductGet (Model model, @PathVariable(name = "id") Long id) {
		Product product = productServiceImpl.getProductById(id);
		if (product == null) {
			model.addAttribute("notification", "Can't found element by id = " + id + "!");
			model.addAttribute("products", productServiceImpl.getListProduct());
			return "products";
		}
		
		ProductNotFormat productNotFormat = new ProductNotFormat();
		productNotFormat.setId(product.getId() +"");
		productNotFormat.setCode(product.getCode());
		productNotFormat.setDescription(product.getDescription());
		productNotFormat.setPrice(product.getPrice() + "");
		model.addAttribute("product", productNotFormat);
		return "editProduct";
	}
	@PostMapping("/editProduct/{id}")
	public String editProductPost1 (Model model, @PathVariable(name = "id") Long id, @ModelAttribute(name = "product") ProductNotFormat productNotFormat) {

		Product product = productServiceImpl.getProductById(id);
		if (product == null) {
			model.addAttribute("notification", "Can't found element by id = " + id + "!");
			model.addAttribute("products", productServiceImpl.getListProduct());
			return "products";
		}
		
		try{
			if(productNotFormat.getDescription() == "" || productNotFormat.getDescription() == null) {
				model.addAttribute("notification", "You must fill the blank!");
				model.addAttribute("product", productNotFormat);
				return "editProduct"; 
			}
			product.setDescription(productNotFormat.getDescription());
			product.setPrice(Double.parseDouble(productNotFormat.getPrice()));
			productServiceImpl.updateProduct(product);
			model.addAttribute("products", productServiceImpl.getListProduct());
			model.addAttribute("notification", "Edit Successfully");
			
		}catch (NumberFormatException e) {
			model.addAttribute("notification", "Price must be double format!");
			model.addAttribute("product", productNotFormat);
			return "editProduct"; 
		}
		
		return "products";
	}
	@GetMapping("/addProduct")
	public String addNewProductGet (Model model) {
		ProductNotFormat product = new ProductNotFormat();
		model.addAttribute("product", product);
		return "addProduct";
	}
	
	@PostMapping("/addProduct")
	public String addNewProductPost (Model model
			, @ModelAttribute(name = "product") ProductNotFormat productNotFormat) {
		Product product = new Product();
		if(!productServiceImpl.getProductByCode(productNotFormat.getCode())) {
			model.addAttribute("notification", "The product with code: " + productNotFormat.getCode() + " already exists");
			model.addAttribute("product", productNotFormat);
			return "addProduct";
		}
		try{
			
			if(productNotFormat.getDescription() == "" || productNotFormat.getDescription() == null || productNotFormat.getCode() == null || productNotFormat.getCode() == "") {
				model.addAttribute("notification", "You must fill the blank!");
				model.addAttribute("product", productNotFormat);
				return "addProduct"; 
			}
			product.setCode(productNotFormat.getCode());
			product.setDescription(productNotFormat.getDescription());
			product.setPrice(Double.parseDouble(productNotFormat.getPrice()));
			
			productServiceImpl.addProduct(product);
			model.addAttribute("products", productServiceImpl.getListProduct());
//			model.addAttribute("notification", "Add Product Successfully");
			
		}catch (NumberFormatException e) {
			model.addAttribute("notification", "Price must be double format!");
			model.addAttribute("product", productNotFormat);
			return "addProduct"; 
		}
		
		return "redirect:/products";
	}
}
