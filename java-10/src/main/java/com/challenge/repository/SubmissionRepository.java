package com.challenge.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.challenge.entity.Submission;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {
	
	public BigDecimal findTopScoreByIdChallengeId(Long challengeId);
	
	@Query(value = "select s from Submission s " + 
					"join s.id.challenge c " +
					"join c.accelerations a " +
					"where a.id = :accelerationId and s.id.challenge.id = :challengeId")
	public List<Submission> findByIdChallengeIdAndAccelerationId(@Param("challengeId") Long challengeId, @Param("accelerationId") Long accelerationId);

}
