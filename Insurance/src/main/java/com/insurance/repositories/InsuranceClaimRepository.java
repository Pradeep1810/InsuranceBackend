package com.insurance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insurance.entities.InsuranceClaim;

public interface InsuranceClaimRepository extends JpaRepository<InsuranceClaim, Long>{

}
