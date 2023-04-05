package com.insurance.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.insurance.entities.User;
import com.insurance.payloads.InsurancePolicyDto;


public interface InsurancePolicyService {
	
	InsurancePolicyDto savePolicy(InsurancePolicyDto policy, Long clientId);
	
	InsurancePolicyDto getPolicy(Long policyId);
	
	InsurancePolicyDto getPolicy(String policyNumber);
	
	List<InsurancePolicyDto> getAllPolicies(String clientName);
	
	List<InsurancePolicyDto> getAllPolicies();
	
	InsurancePolicyDto updatePolicy(InsurancePolicyDto policyDto,Long policyId);
	
	void deletePolicy(Long policyId);

}
