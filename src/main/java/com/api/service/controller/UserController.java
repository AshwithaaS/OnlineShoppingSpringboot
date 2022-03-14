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
import com.api.service.modal.User;
import com.api.service.repository.IUserRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user/api/")
public class UserController {
	@Autowired
	IUserRepository userRepo;

	@GetMapping("/merchants")
	public List<User> getAllMerchants() {
		return userRepo.findAll();
	}

	@PostMapping("/merchants")
	public User createMerchant(@RequestBody User user) {
		user.setActive(1);
		return userRepo.save(user);
	}

	@PutMapping("/merchants/{id}")
	public ResponseEntity<User> updateMerchant(@PathVariable Long id, @RequestBody User userdetails) {
		User user = userRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Merchant not exist with id :" + id));

		user.setFirstName(userdetails.getFirstName());
		user.setLastName(userdetails.getLastName());
		user.setEmail(userdetails.getEmail());

		User updatedEmployee = userRepo.save(user);
		return ResponseEntity.ok(updatedEmployee);
	}

	// delete employee rest api
	@DeleteMapping("/merchants/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteMerchant(@PathVariable Long id) {
		User user = userRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Merchant not exist with id :" + id));

		userRepo.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
