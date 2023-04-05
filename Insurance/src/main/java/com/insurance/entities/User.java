package com.insurance.entities;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "user_address")
	private String userAddress;
	
	@Column(name = "dob")
	private LocalDate dateOfBirth;
	
	@OneToOne
	@JoinColumn(name = "contactId")
	private UserContactInfo contactInfo;
	
	// in  a company a person can have different types of poicies and hence many policies 
	
	@OneToMany(mappedBy = "user" , fetch = FetchType.LAZY)
	private List<InsurancePolicy> policy;
	
	@OneToMany(mappedBy = "user" , fetch = FetchType.LAZY)
	private List<InsuranceClaim> claim;
	
	
	@ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	private Set<Roles> roles = new HashSet<>();
	
}


