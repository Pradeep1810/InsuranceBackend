package com.insurance.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "contact_info" , uniqueConstraints = {
		
		@UniqueConstraint(columnNames = {"email"}) ,
		@UniqueConstraint(columnNames = {"phone_no"})
		
})
public class UserContactInfo {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "email")
	private String emailId;
	
	@Column(name = "phone_no")
	private String phoneNumber;
	
	@OneToOne(mappedBy = "contactInfo" , cascade = CascadeType.ALL)
	@JsonIgnore
	private User user;
	
	
}
