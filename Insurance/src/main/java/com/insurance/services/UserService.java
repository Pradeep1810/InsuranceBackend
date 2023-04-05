package com.insurance.services;


import java.util.List;

import com.insurance.payloads.UserDto;

public interface UserService {

	UserDto registerNewUser(UserDto user);
	
	List<UserDto> getAllClients();
	
	UserDto getClient(Long clientId);
	
	UserDto updateClientInfo(UserDto client , Long clientId);
	
	void deleteClient(Long clientId);
	
}
