package com.insurance.payloads;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insurance.entities.InsurancePolicy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceClaimDto {

	private long id;
	
	@NotBlank
	private String description;
	
	
	@NotNull
	private Boolean claimStatus = false;
	
	@NotNull
	private LocalDate claimDate;
	
	@NotBlank
	private String claimNumber;
	
	@NotNull
	private InsurancePolicyDto insurancePolicy;
}
