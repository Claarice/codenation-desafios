package com.challenge.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.challenge.entity.Company;
import com.challenge.repository.CompanyRepository;
import com.challenge.service.interfaces.CompanyServiceInterface;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CompanyService implements CompanyServiceInterface {
	
	private CompanyRepository companyRepository;
	
	@Override 
	public Optional<Company> findById(Long id) {
		return companyRepository.findById(id);
	}
	
	@Override
	public List<Company> findByAccelerationId(Long accelerationId) {
		return companyRepository.buscarEmpresaPorAceleracao(accelerationId);
	}
	
	@Override 
	public List<Company> findByUserId(Long userId) {
		return companyRepository.buscarEmpresaPorUsuario(userId);
	}
	
	@Override
	public Company save(Company company) {
		return companyRepository.save(company);
	}
}
