package com.insurance.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.insurance.entities.InsurancePolicy;
import com.insurance.payloads.InsuranceClaimDto;


public interface InsuranceClaimService {
	
	InsuranceClaimDto saveClaim(InsuranceClaimDto claimDto , Long policyId);
	
	InsuranceClaimDto getClaim(Long claimId);
	
	List<InsuranceClaimDto> getAllClaims();
	
	InsuranceClaimDto updateClaim(InsuranceClaimDto claimDto , Long claimId);
	
	void deleteClaim(Long claimId);
	
	
	
	

}
