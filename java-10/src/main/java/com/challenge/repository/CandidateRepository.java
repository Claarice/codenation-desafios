package com.challenge.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.challenge.entity.Candidate;
import com.challenge.entity.CandidateId;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
	
	public List<Candidate> findByIdCompanyId(Long companyId);
	
	public List<Candidate> findByIdAccelerationId(Long accelerationId);
	
	public Optional<Candidate> findByIdUserAndIdCompanyAndIdAcceleration(Long userId, Long companyId, Long accelerationId);
	
	public Optional<Candidate> findById(CandidateId id);
}
