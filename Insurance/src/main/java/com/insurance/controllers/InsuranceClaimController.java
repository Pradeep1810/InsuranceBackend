package com.insurance.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.entities.InsurancePolicy;
import com.insurance.exceptions.ResourceNotFoundException;
import com.insurance.payloads.InsuranceClaimDto;
import com.insurance.payloads.InsurancePolicyDto;
import com.insurance.repositories.InsurancePolicyRepository;
import com.insurance.services.InsuranceClaimService;
import com.insurance.services.InsurancePolicyService;



@RestController
@RequestMapping("api/claims")
public class InsuranceClaimController {
	
	@Autowired
	InsuranceClaimService claimService;
	
	@Autowired
	InsurancePolicyService policyService;
	
	@Autowired
	InsurancePolicyRepository policyRepo;
	
	@GetMapping("/all")
	public ResponseEntity<List<InsuranceClaimDto>> getAllClaims(){
		
		List<InsuranceClaimDto> allClaims = this.claimService.getAllClaims();
		
		return ResponseEntity.ok(allClaims);	
	}
	
	@GetMapping("/{claimId}")
	public ResponseEntity<InsuranceClaimDto> getClaim(@PathVariable Long claimId){
		
		InsuranceClaimDto claimDto = this.claimService.getClaim(claimId);
		
		return ResponseEntity.ok(claimDto);
		
	}
	
	@PostMapping("/{policyId}")
	public ResponseEntity<InsuranceClaimDto> addClaim(@RequestBody InsuranceClaimDto claimDto , @PathVariable Long policyId){
		
		InsurancePolicy policy = this.policyRepo.findById(policyId)
				.orElseThrow(() -> new ResourceNotFoundException("policy", "poicy-id", policyId));
				
		
		InsuranceClaimDto claimSaved = this.claimService.saveClaim(claimDto , policyId);
		
		return ResponseEntity.ok(claimSaved);
		
	}
	
    @DeleteMapping("/{claimId}")
    public ResponseEntity<String> deleteClaim(@PathVariable Long claimId){
    	
    	this.claimService.deleteClaim(claimId);
    	
    	return ResponseEntity.ok("Claim Deleted SuccessFully");
    	
    }
    
    @PutMapping("/{claimId}")
    public ResponseEntity<InsuranceClaimDto> updateClaim(@PathVariable Long claimId,@RequestBody InsuranceClaimDto claimDto){
    	 	
    	InsuranceClaimDto claimUpdated = this.claimService.updateClaim(claimDto, claimId);
    	
    	return ResponseEntity.ok(claimUpdated);
    }
	

}
