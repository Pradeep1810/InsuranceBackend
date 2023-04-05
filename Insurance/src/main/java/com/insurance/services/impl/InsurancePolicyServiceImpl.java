package com.insurance.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.insurance.config.AppConstants;
import com.insurance.entities.InsurancePolicy;
import com.insurance.entities.User;
import com.insurance.exceptions.ResourceNotFoundException;
import com.insurance.payloads.InsurancePolicyDto;
import com.insurance.repositories.InsurancePolicyRepository;
import com.insurance.repositories.UserRepository;
import com.insurance.services.InsurancePolicyService;





@Service
public class InsurancePolicyServiceImpl implements InsurancePolicyService{
	
	@Autowired
	InsurancePolicyRepository policyRepo;
	
	@Autowired 
	ModelMapper modelMap;
	
	@Autowired
	UserRepository userRepo;
	
	@Value("$(characters)")
	private String policyCharacters;
	
	private int length = AppConstants.lengthPolicyNumber;
	

	@Override
	public InsurancePolicyDto savePolicy(InsurancePolicyDto policy , Long clientId) {
		
		User client = this.userRepo.findById(clientId)
				.orElseThrow(() -> new ResourceNotFoundException("client","client id", clientId));
		
		
		Optional<InsurancePolicy> policyCheck;
		
		String policyNumber ;
		
		do {
		
		 policyNumber = generateRandomString(length);
		
		policyCheck = this.policyRepo.findByPolicyNumber(policyNumber);
		
		} while(policyCheck.isPresent());
		
		
		policy.setPolicyNumber(policyNumber);
		
    	InsurancePolicy insPolicy = this.dtoToPolicy(policy);
		
		insPolicy.setUser(client);
		
		System.out.println(insPolicy.getUser().getUserName());
		
		 InsurancePolicy savedPolicy = this.policyRepo.save(insPolicy);
		
		
		
		InsurancePolicyDto dto1Policy = this.policyToDto(savedPolicy);
		
		return dto1Policy;
	}

	@Override
	public InsurancePolicyDto getPolicy(Long policyId) {
		
		InsurancePolicy policy = this.policyRepo.findById(policyId).orElseThrow(
				() -> new ResourceNotFoundException("policy", "policy-id", policyId));
		
		System.out.println(policy.getPolicyNumber());
		
		InsurancePolicyDto policyFoundDto = this.policyToDto(policy);
		
		return policyFoundDto;
	}

	@Override
	public InsurancePolicyDto getPolicy(String policyNumber) {
		
		
		
		return null;
	}

	@Override
	public List<InsurancePolicyDto> getAllPolicies(String clientName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InsurancePolicyDto> getAllPolicies() {
		
		List<InsurancePolicy> allPolicies = this.policyRepo.findAll();
		
		List<InsurancePolicyDto> policiesDto = allPolicies.stream().map(
				policy -> this.policyToDto(policy)).collect(Collectors.toList());
		
		return policiesDto;
	}

	@Override
	public InsurancePolicyDto updatePolicy(InsurancePolicyDto policyDto, Long policyId) {
		
		// only add the fields like coverage amount to be updated and also the end date 
		
		InsurancePolicy policy = this.policyRepo.findById(policyId).orElseThrow(
				() -> new ResourceNotFoundException("policy", "policy-number", policyId));
		
		policy.setCoverageAmount(policyDto.getCoverageAmount());
		policy.setPremiumAmount(policyDto.getPremiumAmount());
		policy.setPolicyEnd(policyDto.getPolicyEnd());
		
		InsurancePolicy policySaved = this.policyRepo.save(policy);
		
		InsurancePolicyDto policySavedDto = this.policyToDto(policySaved);

		return policySavedDto;
	}

	@Override
	public void deletePolicy(Long policyId) {
		
		InsurancePolicy policy = this.policyRepo.findById(policyId).orElseThrow(
				() -> new ResourceNotFoundException("policy", "policy-id", policyId));
		
		this.policyRepo.delete(policy);
		
	}
	
	public InsurancePolicyDto policyToDto(InsurancePolicy policy) {
		
		InsurancePolicyDto policyDto = this.modelMap.map(policy, InsurancePolicyDto.class);
		
		return policyDto;
		
		
	}
	
     public InsurancePolicy dtoToPolicy(InsurancePolicyDto dtoPolicy) {
		
		InsurancePolicy policy = this.modelMap.map(dtoPolicy, InsurancePolicy.class);
		
		return policy;
		
		
	}
     
     // method to generate policy number 
     
     public static String generateRandomString(int length) {
         String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
         StringBuilder sb = new StringBuilder(length);
         Random random = new Random();

         for (int i = 0; i < length; i++) {
             int index = random.nextInt(characters.length());
             sb.append(characters.charAt(index));
         }

         return sb.toString();
     }

}
