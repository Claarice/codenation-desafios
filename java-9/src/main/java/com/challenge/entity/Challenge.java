package com.challenge.entity;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;

@Data
@Entity(name = "challenge")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Challenge {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min = 1, max = 100)
	private String name;
	
	@NotNull
	@Size(min = 1, max = 50)
	private String slug;
	
	@OneToMany(mappedBy = "challenge")
	private List<Submission> submissions;
	
	@OneToMany(mappedBy = "challenge") 
	private List<Acceleration> accelerations;

	@CreatedDate
	private LocalDateTime createdAt;
}
