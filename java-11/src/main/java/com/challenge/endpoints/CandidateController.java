package com.challenge.endpoints;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.dto.CandidateDTO;
import com.challenge.entity.Candidate;
import com.challenge.mappers.CandidateMapper;
import com.challenge.service.impl.CandidateService;

@RestController
@RequestMapping("candidate")
public class CandidateController {
	
	@Autowired
	private CandidateService candidateService;
	
	@Autowired
	private CandidateMapper candidateMapper;
	
	@GetMapping
	public List<CandidateDTO> findAll(@RequestParam(value = "companyId") Long companyId) {
		return candidateMapper.map(candidateService.findByCompanyId(companyId));
	}
	
		
	@GetMapping("/{userId}/{companyId}/{accelerationId}") 
	public CandidateDTO findById(@PathVariable(value = "userId") Long userId,
			@PathVariable(value = "companyId") Long companyId,
			@PathVariable(value = "accelerationId") Long accelerationId) {

		Optional<Candidate> candidate = candidateService.findById(userId, companyId, accelerationId);

		return candidateMapper.map(candidate.get()); 
	}
	
	@GetMapping("/{accelerationId}") 
	public List<CandidateDTO> findByAccelerationId(@PathVariable(value = "accelerationId") Long accelerationId) {

		return candidateMapper.map(candidateService.findByAccelerationId(accelerationId)); 
	}
	
}
