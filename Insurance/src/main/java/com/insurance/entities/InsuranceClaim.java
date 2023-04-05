package com.insurance.entities;

import java.time.LocalDate;

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
public class InsuranceClaim {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "claim_description")
	private String description;
	
	@Column(name = "claim_status")
	private Boolean claimStatus = false; // false till the claim is cleared
	
	@Column(name = "claim_date")
	private LocalDate claimDate;
	
	@Column(name = "claim_number")
	private String claimNumber;
	
	@OneToOne(mappedBy = "insuranceClaim" , fetch = FetchType.EAGER )
	private InsurancePolicy insurancePolicy;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	
	
}
