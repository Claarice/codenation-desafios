package com.challenge.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.challenge.entity.Acceleration;

@Repository
public interface AccelerationRepository extends JpaRepository<Acceleration, Long> {
	
	public Optional<Acceleration> findByName(String name);
	
	@Query(value = "select a from Acceleration a " + 
					"join a.id.candidates c " + 
					"where c.id.company.id = :companyId")
	public List<Acceleration> buscarAceleracoesPorEmpresa(@Param("companyId") Long companyId);

}
