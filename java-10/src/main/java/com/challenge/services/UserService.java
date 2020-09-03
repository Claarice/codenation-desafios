package com.challenge.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.challenge.entity.User;
import com.challenge.repository.UserRepository;
import com.challenge.service.interfaces.UserServiceInterface;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements UserServiceInterface {
	
	private UserRepository userRepository;
	
	@Override
	public User save(User user) {
		return userRepository.save(user);
	}
	
	@Override
	public Optional<User> findById(Long UserId) {
		return userRepository.findById(UserId);
	}
	
	@Override
	public List<User> findByAccelerationName(String name) {
		return userRepository.findByCandidatesIdAccelerationName(name);
	}
	
	@Override
	public List<User> findByCompanyId(Long companyId) {
		return userRepository.findByCandidatesIdCompanyId(companyId);
	}
}
