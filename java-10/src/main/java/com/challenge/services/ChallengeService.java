package com.challenge.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.challenge.entity.Challenge;
import com.challenge.repository.ChallengeRepository;
import com.challenge.service.interfaces.ChallengeServiceInterface;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ChallengeService implements ChallengeServiceInterface {
	
	private ChallengeRepository challengeRepository;
	
	@Override
	public List<Challenge> findByAccelerationIdAndUserId(Long accelerationId, Long userId) {
		return challengeRepository.buscarDesafiosPorAceleracaoEUsuario(accelerationId, userId);
	}
	
	@Override
	public Challenge save(Challenge challenge) {
		return challengeRepository.save(challenge);
	}
}
