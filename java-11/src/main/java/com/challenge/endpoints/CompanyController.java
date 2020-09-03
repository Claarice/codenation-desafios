package com.challenge.endpoints;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.entity.Company;
import com.challenge.repository.CompanyRepository;
import com.challenge.service.impl.CompanyService;

@RestController
@RequestMapping("company")
public class CompanyController {

	@Autowired
	private CompanyService companyService;
	
	@GetMapping
	public List<Company> findByRequestParams(
            @RequestParam(value = "userId", required = false, defaultValue = "0") Long userId,
            @RequestParam(value = "accelerationId", required = false, defaultValue = "0") Long accelerationId) {
		
		if (userId != 0) {
			return companyService.findByUserId(userId);
		} else if (accelerationId != 0) {
			return companyService.findByAccelerationId(accelerationId);
		}
		
		return null;
	}
	
	@GetMapping("/{id}")
	public Optional<Company> findById(@PathVariable("id") Long id) {
		return Optional.ofNullable(companyService.findById(id)).orElse(null);
	}
	
}
