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

import com.insurance.entities.User;
import com.insurance.exceptions.ResourceNotFoundException;
import com.insurance.payloads.InsurancePolicyDto;
import com.insurance.repositories.UserRepository;
import com.insurance.services.InsurancePolicyService;

@RestController
@RequestMapping("api/policies")
public class InsurancePolicyController {

	@Autowired
	InsurancePolicyService policyService;
	
	@Autowired 
	UserRepository userRepo;
	
	@GetMapping("/")
	public ResponseEntity<List<InsurancePolicyDto>> getAllPolicies(){
		
		List<InsurancePolicyDto> allPolicies = this.policyService.getAllPolicies();
		
		return ResponseEntity.ok(allPolicies);
	}
	
	@GetMapping("/{policyId}")
	public ResponseEntity<InsurancePolicyDto> getPolicy(@PathVariable Long policyId){
		
		InsurancePolicyDto policy = this.policyService.getPolicy(policyId);
		
		return ResponseEntity.ok(policy);
		
	}
	
	@PostMapping("/{clientId}/add")
	public ResponseEntity<InsurancePolicyDto> addPolicy(@RequestBody InsurancePolicyDto policyDto , @PathVariable Long clientId){
		
		
		InsurancePolicyDto policySaved = this.policyService.savePolicy(policyDto , clientId);
		
		return ResponseEntity.ok(policySaved);
			
	}
	
	@PutMapping("/{policyId}")
	public ResponseEntity<InsurancePolicyDto> updatePolicy(@PathVariable Long policyId,@RequestBody InsurancePolicyDto policyDto){
		
		InsurancePolicyDto policyUpdated = this.policyService.updatePolicy(policyDto, policyId);
		
		return ResponseEntity.ok(policyUpdated);
	
	}
	
	@DeleteMapping("/{policyId}")
	public ResponseEntity<String> deletePolicy(@PathVariable Long policyId){
		
		InsurancePolicyDto policy = this.policyService.getPolicy(policyId);
		
		this.policyService.deletePolicy(policyId);
		
		return ResponseEntity.ok("Policy deeted Successfully " +policy.getPolicyNumber());
		
		
	}
	
}
