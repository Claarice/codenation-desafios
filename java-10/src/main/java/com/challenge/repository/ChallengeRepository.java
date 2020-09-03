package com.challenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.challenge.entity.Challenge;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
	
	@Query(value = "select c from Challenge c " + 
					"join c.accelerations a " + 
					"join a.id.candidates ca " +
					"where ca.id.acceleration.id = :accelerationId " +
					"and ca.id.user.id = :userId")
	public List<Challenge> buscarDesafiosPorAceleracaoEUsuario(@Param("accelerationId") Long accelerationId, @Param("userId") Long userId);

}
