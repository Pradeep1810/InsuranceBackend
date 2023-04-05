package com.insurance.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insurance.entities.UserContactInfo;

public interface UserContactInfoRepository extends JpaRepository<UserContactInfo, Long> {
	
	public Optional<UserContactInfo> findByEmailId(String emailId);
	
	public Optional<UserContactInfo> findByPhoneNumber(String phoneNumber);

}
