package com.insurance.controllers;

import java.lang.System.Logger;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.entities.User;
import com.insurance.exceptions.ApiException;
import com.insurance.payloads.JwtRequest;
import com.insurance.payloads.JwtResponse;
import com.insurance.payloads.UserDto;
import com.insurance.repositories.UserRepository;
import com.insurance.security.InsuranceUserDetailsService;
import com.insurance.security.JwtTokenHelper;
import com.insurance.services.UserService;





@RestController
@RequestMapping("/api/auth")
public class JwtAuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenHelper jwtTokenUtil;

	@Autowired
	private InsuranceUserDetailsService userDetailsService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepo;
	
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		
		authenticate(authenticationRequest.getUserName(), authenticationRequest.getPassword());
		
		
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUserName());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	private void authenticate(String userName, String password) throws Exception {
		
		try {
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
	
	@PostMapping("/signup")
	public String registerUser(@RequestBody UserDto userDto) throws ApiException{
		
		
       Optional<User> userExist = this.userRepo.findByUserName(userDto.getUserName());
		
		if(userExist.isPresent()) {
			
		  throw new ApiException("UserName already exists in the system "  +userExist.get().getUserName());
		}
		else {
			
			
		UserDto user = this.userService.registerNewUser(userDto);
		
		return "Registered";
		
		}
		
	}

}

