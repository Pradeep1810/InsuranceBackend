package com.insurance.payloads;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.insurance.entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InsurancePolicyDto {

	private long id;
	
	
	private String policyNumber;
	
	@NotBlank
	private String policyType;
	
	@Min(value = 10000) // minimum coverage of 10,000
	private long coverageAmount;
	
	@Min(value = 3000) // minimum premium of 3,000
	private long premiumAmount;
	
	@NotNull
	 private LocalDate policyStart;
	 
	@NotNull
	 private LocalDate policyEnd;
	
	//
	 private UserDto user;
	
   
}
