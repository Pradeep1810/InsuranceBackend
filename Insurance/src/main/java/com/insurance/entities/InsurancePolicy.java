package com.insurance.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class InsurancePolicy {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "policy_number")
	private String policyNumber;
	
	@Column(name = "policy_type")
	private String policyType;
	
	@Column(name = "coverage_amount")
	private long coverageAmount;
	
	@Column(name = "premium_amount")
	private long premiumAmount;
	
	 @Column(name = "start_date")
	 private LocalDate policyStart;
	 
	 @Column(name = "end_date")
	 private LocalDate policyEnd;
	 
	 @OneToOne(fetch = FetchType.LAZY)
	 @JsonIgnore
	 private InsuranceClaim insuranceClaim;
	
	 @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	 private User user;
	
	
}
