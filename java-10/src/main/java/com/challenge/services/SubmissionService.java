package com.challenge.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.challenge.entity.Submission;
import com.challenge.repository.SubmissionRepository;
import com.challenge.service.interfaces.SubmissionServiceInterface;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubmissionService implements SubmissionServiceInterface {
	
	private SubmissionRepository submissionRepository;
	
	@Override
	public BigDecimal findHigherScoreByChallengeId(Long challengeId) {
		return submissionRepository.findTopScoreByIdChallengeId(challengeId);
	}
	
	@Override
	public List<Submission> findByChallengeIdAndAccelerationId(Long challengeId, Long accelerationId) {
		return submissionRepository.findByIdChallengeIdAndAccelerationId(challengeId, accelerationId);
	}
	
	@Override
	public Submission save(Submission submission) {
		return submissionRepository.save(submission);
	}
}
