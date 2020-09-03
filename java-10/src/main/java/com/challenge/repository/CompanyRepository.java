package com.challenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.challenge.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
	
	@Query(value = "select c from Company c " + 
					"join c.candidates ca " + 
					"where ca.id.acceleration.id = :accelerationId")
	public List<Company> buscarEmpresaPorAceleracao(@Param("accelerationId")Long accelerationId);
	
	@Query(value = "select c from Company c " +
					"join c.candidates ca " + 
					"where ca.id.user.id = :userId")
	public List<Company> buscarEmpresaPorUsuario(@Param("userId") Long userId);
}
