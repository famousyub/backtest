package com.omnia.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omnia.app.model.Company;
import com.omnia.app.repository.CompanyRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class GuestController {
	
	@Autowired
	CompanyRepository comporepo ;
	
	
	@GetMapping("/guest")
ResponseEntity<?> getAllCompanies1(){
		
		List<Company> companies = comporepo.findAll();
		
		return ResponseEntity.ok().body(companies);
	}
	
	
	@GetMapping("/guest/companies")
	ResponseEntity<?> getAllCompanies(){
		
		List<Company> companies = comporepo.findAll();
		
		return ResponseEntity.ok().body(companies);
	}

}
