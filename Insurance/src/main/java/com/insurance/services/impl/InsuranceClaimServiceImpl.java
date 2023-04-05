package com.insurance.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.entities.InsuranceClaim;
import com.insurance.entities.InsurancePolicy;
import com.insurance.exceptions.ResourceNotFoundException;
import com.insurance.payloads.InsuranceClaimDto;
import com.insurance.repositories.InsuranceClaimRepository;
import com.insurance.repositories.InsurancePolicyRepository;
import com.insurance.services.InsuranceClaimService;

@Service
public class InsuranceClaimServiceImpl implements InsuranceClaimService {

	@Autowired
	InsuranceClaimRepository claimRepository;
	
	@Autowired
	InsurancePolicyRepository policyRepo;
	
	@Autowired
	ModelMapper modelMap;
	
	
	@Override
	public InsuranceClaimDto saveClaim(InsuranceClaimDto claimDto , Long policyId) {
		
		InsurancePolicy policy = this.policyRepo.findById(policyId)
				.orElseThrow(() -> new ResourceNotFoundException("policy", "poicy-id", policyId));
				
		// fetching the policy
		
		InsuranceClaim claim = this.dtoToInsuranceClaim(claimDto); // converting the claimdto to claim
		
		claim.setUser(policy.getUser());
		
		InsuranceClaim claimSaved = this.claimRepository.save(claim);
		
		//adding the claim to the policy 
		
		InsurancePolicy policySave = policy;
		
		policySave.setInsuranceClaim(claim);
		
		this.policyRepo.save(policySave); // saving the policy - claim 
		
		InsuranceClaimDto claimSavedDto = this.claimToDto(claimSaved);
		
		return claimSavedDto;
	}

	@Override
	public InsuranceClaimDto getClaim(Long claimId) {
		
		InsuranceClaim claim = this.claimRepository.findById(claimId)
				.orElseThrow(() -> new ResourceNotFoundException("claim", "claim-id", claimId));
		
		System.out.println(claim.getClaimNumber());
		
		InsuranceClaimDto claimDto = this.claimToDto(claim);
		
	//	InsuranceClaimDto claimDto = new InsuranceClaimDto();
		
		return claimDto;
	}

	@Override
	public List<InsuranceClaimDto> getAllClaims() {
		
		List<InsuranceClaim> allClaims = this.claimRepository.findAll();
		
		List<InsuranceClaimDto> dtoAllClaims = allClaims.stream().
				map(claim -> this.claimToDto(claim)).collect(Collectors.toList());
		
		return dtoAllClaims;
	}

	@Override
	public InsuranceClaimDto updateClaim(InsuranceClaimDto claimDto, Long claimId) {
		
		InsuranceClaim claim = this.claimRepository.findById(claimId)
				.orElseThrow(() -> new ResourceNotFoundException("claim", "claim-id", claimId));
		
		
		Optional<InsurancePolicy> policyLinked = this.policyRepo.findById(claim.getInsurancePolicy().getId());
		
		if(policyLinked.isPresent()) {
			
			claim.setClaimStatus(claimDto.getClaimStatus());
			claim.setDescription(claimDto.getDescription());
			
			InsuranceClaim claimUpdated = this.claimRepository.save(claim);
			
			InsuranceClaimDto claimSavedDto = this.claimToDto(claimUpdated);
			
			return claimSavedDto;
				
		} else {
			
			return null;
			
		}
		
		
	}

	@Override
	public void deleteClaim(Long claimId) {
		
		
		InsuranceClaim claim = this.claimRepository.findById(claimId).orElseThrow(
				() -> new ResourceNotFoundException("claim", "claim-id", claimId));
		
		this.claimRepository.delete(claim);
		
	}
	
	public InsuranceClaim dtoToInsuranceClaim(InsuranceClaimDto dtoClaim) {
		
		InsuranceClaim claim  = this.modelMap.map(dtoClaim, InsuranceClaim.class);
		
		return claim;
	
	}
	
	public InsuranceClaimDto claimToDto(InsuranceClaim claim) {
		
		InsuranceClaimDto dtoClaim = this.modelMap.map(claim, InsuranceClaimDto.class);
		
		return dtoClaim;
		
		
	}

}
