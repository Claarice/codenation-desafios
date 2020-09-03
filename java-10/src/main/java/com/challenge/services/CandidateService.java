package com.challenge.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.challenge.entity.Candidate;
import com.challenge.entity.CandidateId;
import com.challenge.repository.CandidateRepository;
import com.challenge.service.interfaces.CandidateServiceInterface;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CandidateService implements CandidateServiceInterface {
	
	private CandidateRepository candidateRepository;
	
	@Override
	public Optional<Candidate> findById(CandidateId id){
		return candidateRepository.findById(id);
	};
		
	@Override 
	public Optional<Candidate> findById(Long userId, Long companyId, Long accelerationId) {
		return candidateRepository.findByIdUserAndIdCompanyAndIdAcceleration(userId, companyId, accelerationId);
	}
	
	@Override
	public List<Candidate> findByCompanyId(Long companyId) {
		return candidateRepository.findByIdCompanyId(companyId);
	}

	@Override
    public List<Candidate> findByAccelerationId(Long accelerationId) {
    	return candidateRepository.findByIdAccelerationId(accelerationId);
    }
	
	@Override 
	public Candidate save(Candidate candidate) {
		return candidateRepository.save(candidate);
	}
}