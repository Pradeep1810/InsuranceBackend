package com.insurance.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insurance.entities.InsurancePolicy;

public interface InsurancePolicyRepository extends JpaRepository<InsurancePolicy, Long>{
	
	
	public Optional<InsurancePolicy> findByPolicyNumber(String policyNumber); 
	
	public List<InsurancePolicy>  findByUserUserName(String userName);
	

}
