package com.insurance.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.insurance.config.AppConstants;
import com.insurance.entities.DuplicateEntryException;
import com.insurance.entities.InsuranceClaim;
import com.insurance.entities.Roles;
import com.insurance.entities.User;
import com.insurance.entities.UserContactInfo;
import com.insurance.exceptions.ApiException;
import com.insurance.exceptions.ResourceNotFoundException;
import com.insurance.payloads.UserDto;
import com.insurance.repositories.InsuranceClaimRepository;
import com.insurance.repositories.RolesRepository;
import com.insurance.repositories.UserContactInfoRepository;
import com.insurance.repositories.UserRepository;
import com.insurance.services.UserService;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	RolesRepository roleRepos;
	
	@Autowired
	ModelMapper modelMap;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserContactInfoRepository contactRepo;
	
	
	

	@Override
	public UserDto registerNewUser(UserDto userDto){
		
		User user = this.userDtoToUser(userDto);
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		Roles role = this.roleRepos.findById(AppConstants.Role_USER).get();
		user.getRoles().add(role);
		
		// this checks for user in the database to avoid multiple signups 
		
		Optional<UserContactInfo> emailContact = this.contactRepo.findByEmailId(user.getContactInfo().getEmailId());
		
		Optional<UserContactInfo> phoneNumber = this.contactRepo.findByPhoneNumber(user.getContactInfo().getPhoneNumber());
		
		
		 if(emailContact.isPresent() || phoneNumber.isPresent() ) {
			
			// change that so it gives a response Entity 
		  
		  throw new DuplicateEntryException("Email or phone number is already resgistered");
			
		}
		
		else {
			
			UserContactInfo contact = new UserContactInfo();
			
			contact.setEmailId(user.getContactInfo().getEmailId());
			
			contact.setPhoneNumber(user.getContactInfo().getPhoneNumber());
			
			

		UserContactInfo contactSaved = this.contactRepo.save(contact);
		
		// setting the contact info to our user object
		
		user.setContactInfo(contactSaved);
		
		User registeredUser = this.userRepo.save(user);
		
		UserContactInfo addUser = contactSaved;
		
		addUser.setUser(registeredUser);
		
		this.contactRepo.save(addUser);
		
		UserDto registeredDto = this.usertoDto(registeredUser);
		
		return registeredDto;
		
		}
		
	}

	@Override
	public List<UserDto> getAllClients() {
		
		List<User> users = this.userRepo.findAll();
		
		List<UserDto> usersDto = users.stream().map(user -> this.usertoDto(user)).collect(Collectors.toList());
		
		return usersDto;
	}

	@Override
	public UserDto getClient(Long clientId) {
		
		System.out.print(clientId);
		
		User user = this.userRepo.findById(clientId).orElseThrow(
				() -> new ResourceNotFoundException("Cliet", "client-id", clientId));
	
		UserDto clientDto = this.usertoDto(user);
		
		return clientDto;
	}

	@Override
	public UserDto updateClientInfo(UserDto client , Long clientId) {
		
		User clientFound = this.userRepo.findById(clientId).orElseThrow(
				() -> new ResourceNotFoundException("Client", "client-id", clientId));	
		
		
		clientFound.setUserAddress(client.getUserAddress());
		clientFound.setPassword(this.passwordEncoder.encode(client.getPassword()));
		
		UserContactInfo contact = clientFound.getContactInfo();
		
		contact.setEmailId(client.getContactInfo().getEmailId());
		contact.setPhoneNumber(client.getContactInfo().getPhoneNumber());
		
		this.contactRepo.save(contact);
		
		User savedClient = this.userRepo.save(clientFound);
		
		UserDto clientSavedDto = this.usertoDto(savedClient);
		
		return clientSavedDto;
	}

	@Override
	public void deleteClient(Long clientId) {
		
		User user = this.userRepo.findById(clientId).orElseThrow(
				() -> new ResourceNotFoundException("Cliet", "client-id", clientId));
		this.userRepo.deleteById(clientId);
		
	}
	
public User userDtoToUser(UserDto udto) {
		
		User user = this.modelMap.map(udto, User.class);
		
		return user;		
	}
	
	public UserDto usertoDto(User user) {
		
		UserDto udto = this.modelMap.map(user, UserDto.class);

		return udto;
	}

}
