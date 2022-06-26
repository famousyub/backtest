package com.omnia.app;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.omnia.app.model.Company;
import com.omnia.app.model.Employee;
import com.omnia.app.model.Role;
import com.omnia.app.repository.CompanyRepository;
import com.omnia.app.repository.RoleRepository;
import com.omnia.app.repository.UserRepository;
@Component
public class ActivityListen  implements CommandLineRunner{
	
	@Autowired
	UserRepository userropo;
	
	@Autowired
	RoleRepository rolerepo;
	@Autowired
	CompanyRepository comporepo;
	
	@Autowired
    private PasswordEncoder bcryptEncoder;
	

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
	
	
		 Employee emp =new Employee(); 
		  Long id = Long.parseLong("1"); 
		  Long id_role = Long.parseLong("1"); 
		 // employees (id, created_at, updated_at,account_enabled, email, first_name, last_name, password, username,company_id) // 
		  Company com= comporepo.findById(id).get(); //
		  emp.setEmail("monsieurhassen2.2019@gmail.com"); // 
		  emp.setFirstName("hassen12"); //
		  emp.setLastName("hassen21"); // 
		  emp.setUsername("hassen21"); //
		  emp.setCompany(com); // 
		  Set<Role> roles = new HashSet<>(); // 
		  Role role = rolerepo.findById(id_role).get(); // 
		  roles.add(role); // 
		  emp.setRoles(roles);
		  // 
		  String password1 = bcryptEncoder.encode("147852369az"); // //
		  emp.setPassword(password1); // // 
		  userropo.save(emp);
		 		
	
		
		
		
		
	}

}
