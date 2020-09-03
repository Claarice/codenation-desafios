package com.challenge.endpoints;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.entity.User;
import com.challenge.repository.UserRepository;
import com.challenge.service.impl.UserService;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;
			
	@GetMapping("/{id}")
	public Optional<User> findById(@PathVariable("id") Long id) {
		return Optional.ofNullable(userService.findById(id)).orElse(null);
	}
	
	@GetMapping
	public List<User> findByAccelerationNameOrCompanyId(
			@RequestParam(value = "companyId", required = false, defaultValue = "0") Long companyId,
			@RequestParam(value = "accelerationName", required = false, defaultValue = "") String accelerationName
			) {
		if (companyId != 0) {
			return userService.findByCompanyId(companyId);
		} else if (!accelerationName.isEmpty()) {
			return userService.findByAccelerationName(accelerationName);
		}
		
		return null;
	}
	

}	
