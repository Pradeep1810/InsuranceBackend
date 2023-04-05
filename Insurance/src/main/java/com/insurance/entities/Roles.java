package com.insurance.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Roles {

	@Id
	private long id;
	
	private String roleTitle;
	
	@ManyToMany(mappedBy = "roles" )
	private Set<User> user;
	
}
