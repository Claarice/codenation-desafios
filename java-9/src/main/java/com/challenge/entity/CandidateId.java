package com.challenge.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class CandidateId implements Serializable {
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Acceleration acceleration;
	
	@ManyToOne
	private Company company;
}
