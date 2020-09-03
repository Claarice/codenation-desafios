package com.challenge.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity(name = "user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min = 1, max = 100)
	private String fullName;

	@NotNull
	@Email
	@Size(min = 1, max = 100)
	private String email;
	
	@NotNull
	@Size(min = 1, max = 50)
	private String nickname;

	@NotNull
	@Size(min = 1, max = 255)
	private String password;
	
	@OneToMany(mappedBy = "user")
	private List<Submission> submissions;
	
	@OneToMany(mappedBy = "user")
	private List<Candidate> candidates;

	@CreatedDate
	private LocalDateTime createdAt;
}
