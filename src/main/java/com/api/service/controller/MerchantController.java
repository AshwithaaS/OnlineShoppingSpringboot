package com.api.service.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.service.exception.ResourceNotFoundException;
import com.api.service.modal.Category;
import com.api.service.modal.Product;
import com.api.service.repository.ICategoryRepository;
import com.api.service.repository.IProductRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/merchant/api/")
public class MerchantController {
	@Autowired
	ICategoryRepository catRepo;

	@Autowired
	IProductRepository proRepo;

	@GetMapping("/categories")
	public List<Category> getAllCategory() {
		return catRepo.findAll();
	}

	@PostMapping("/categories")
	public Category createCategory(@RequestBody Category Category) {
		return catRepo.save(Category);
	}

	@PutMapping("/categories/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category Categorydetails) {
		Category Category = catRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category not exist with id :" + id));
		Category.setCategoryName(Categorydetails.getCategoryName());
		Category updatedEmployee = catRepo.save(Category);
		return ResponseEntity.ok(updatedEmployee);
	}

	// delete employee rest api
	@DeleteMapping("/categories/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteCategory(@PathVariable Long id) {
		Category Category = catRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category not exist with id :" + id));

		catRepo.delete(Category);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/products")
	public List<Product> getAllProducts() {
		return proRepo.findAll();
	}

	@PostMapping("/products")
	public Product createProduct(@RequestBody Product Product) {
		return proRepo.save(Product);
	}

	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product Productdetails) {
		Product Product = proRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not exist with id :" + id));
		Product.setProductName(Productdetails.getProductName());
		Product.setProductDescription(Productdetails.getProductDescription());
		Product.setProductPrice(Productdetails.getProductPrice());
		Product.setProductUnit(Productdetails.getProductUnit());
		Product updatedEmployee = proRepo.save(Product);
		return ResponseEntity.ok(updatedEmployee);
	}

	// delete employee rest api
	@DeleteMapping("/products/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable Long id) {
		Product Product = proRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not exist with id :" + id));

		proRepo.delete(Product);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
