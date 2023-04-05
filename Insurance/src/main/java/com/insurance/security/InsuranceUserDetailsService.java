package com.insurance.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.insurance.entities.User;
import com.insurance.exceptions.ResourceNotFoundException;
import com.insurance.repositories.UserRepository;
import com.insurance.services.impl.UserDetailsImpl;



@Service
public class InsuranceUserDetailsService implements UserDetailsService{

	@Autowired
	UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
			
		User user = this.userRepo.findByUserName(userName).
				orElseThrow(() ->new ResourceNotFoundException(userName, "failed to load user by user name", 0));
		
		return UserDetailsImpl.buildDetails(user);
	}

}
