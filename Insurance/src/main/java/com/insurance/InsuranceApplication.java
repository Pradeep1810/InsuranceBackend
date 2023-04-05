package com.insurance;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.insurance.config.AppConstants;
import com.insurance.entities.Roles;
import com.insurance.repositories.RolesRepository;
import com.insurance.repositories.UserRepository;

@SpringBootApplication
public class InsuranceApplication implements CommandLineRunner{

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	RolesRepository roleRepo;
	
	@Bean
	public ModelMapper modelMapper () {
		
		
		return new ModelMapper();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(InsuranceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
//		String pass = this.passwordEncoder.encode("pradeep980@R");
//		
//		System.out.print(pass + " password");
		
		try {
			Roles role = new Roles();
			role.setId(AppConstants.Role_ADMIN);
			role.setRoleTitle("Role_ADMIN");
			
			Roles role2 = new Roles();
			role2.setId(AppConstants.Role_USER);
			role2.setRoleTitle("Role_USER");
			
			List<Roles> roles = List.of(role,role2);
			
			this.roleRepo.saveAll(roles);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

}
