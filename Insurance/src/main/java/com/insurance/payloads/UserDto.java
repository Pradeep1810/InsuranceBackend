package com.insurance.payloads;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insurance.entities.UserContactInfo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

     private long id;
	
	@NotBlank
	private String userName;
	
	@NotBlank
	private String userAddress;
	
	@NotNull
	private LocalDate dateOfBirth;
	
	@NotBlank
	private String password;

	@NotNull
	private UserContactInfo contactInfo;
}
