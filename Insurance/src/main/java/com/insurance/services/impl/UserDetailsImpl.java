package com.insurance.services.impl;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.insurance.entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {
	
	
	private long id;
	
	private String userName;
	
	private String password;
	
	private String userAddress;
	
	private LocalDate dateOfBirth;
	
	
	
	
	private Collection<? extends GrantedAuthority> authorities;
	
	
	public static UserDetailsImpl buildDetails(User user) {
	    List<GrantedAuthority> authorities = user.getRoles()
	            .stream()
	            .map(role -> new SimpleGrantedAuthority(role.getRoleTitle()))
	            .collect(Collectors.toList());

	    return new UserDetailsImpl(
	            
	    		user.getUserId(),
	    		user.getUserName(),
	    		user.getPassword(),
	    		user.getUserAddress(),
	    		user.getDateOfBirth(),
	    		authorities
	    );
	}

	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	
	

}

