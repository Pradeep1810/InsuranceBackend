package com.insurance.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.payloads.UserDto;
import com.insurance.services.UserService;

@RestController
@RequestMapping("api/clients")
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("/all")
	public ResponseEntity<List<UserDto>> getAllClients(){
		
		List<UserDto> allClients = this.userService.getAllClients();
		
		return ResponseEntity.ok(allClients);
		
	}
	
	@GetMapping("/{clientId}")
	public ResponseEntity<UserDto> getClient(@PathVariable Long clientId){
		
		UserDto clientDto = this.userService.getClient(clientId);
		
		return ResponseEntity.ok(clientDto);
	
	}
	
	@DeleteMapping("/{clientId}")
	public ResponseEntity<String> deleteClient(@PathVariable Long clientId){
		
		UserDto clientDto = this.userService.getClient(clientId);
		
		this.userService.deleteClient(clientId);
		
		return ResponseEntity.ok("Successfully deleted client " +clientDto.getUserName());	
	}
	
	@PutMapping("/{clientId}")
	public ResponseEntity<UserDto> updateClient(@RequestBody UserDto clientDto , @PathVariable Long clientId){
		
		UserDto updatedClient = this.userService.updateClientInfo(clientDto, clientId);
		
		return ResponseEntity.ok(updatedClient);
		
	}
	
	
	
}
