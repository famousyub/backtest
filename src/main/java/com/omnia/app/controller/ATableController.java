package com.omnia.app.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omnia.app.model.ATable;
import com.omnia.app.model.Area;
import com.omnia.app.model.Company;
import com.omnia.app.payload.AreaRequest;
import com.omnia.app.repository.ATableRepository;
import com.omnia.app.repository.AreaRepository;
import com.omnia.app.security.CurrentUser;
import com.omnia.app.security.UserPrincipal;

@RestController
@RequestMapping("/api/table")
@CrossOrigin("*")
public class ATableController {
	
	@Autowired 
	ATableRepository atrepo;
	
	@Autowired
	AreaRepository arepo;
	
	@GetMapping("/listatable")
	ResponseEntity<?> getListTables(){
		List <ATable> listable= atrepo.findAll();
		
		return ResponseEntity.ok().body(listable);
		
		
	}
	
@PostMapping("/addatable/{areaid}")
	
	ResponseEntity<?> addArea(@RequestBody ATable ar1,@PathVariable("areaid") String id , @CurrentUser UserPrincipal currentUser){
		
try {
			
	
			long iduser  = currentUser.getId();
			
			System.out.println(id);
			
			Long id_ = Long.parseLong(id);
			
			Area ar = arepo.findById(id_).get();
			
			ar1.setServerCall(false);
		
			ar1.setArea(ar);
			
			atrepo.save(ar1);
			
		
			
			
			
			return ResponseEntity.ok().body(ar1);
			
		}catch(Exception  e) {
			
			 	return ResponseEntity.badRequest().body("somethingwrong");
			
		}
		
		
		
		
	}

@PutMapping("/updatecallservice/{id}")
ResponseEntity<?> putAreaService(@PathVariable("id") String id) {
	Long id_ = Long.parseLong(id);
	ATable ar_ = atrepo.findById(id_).get();
	
	if(ar_.equals(null))return ResponseEntity.badRequest().body("something wrond");
	
	ar_.setLastServerCall(new Date());
	atrepo.save(ar_);
	return ResponseEntity.ok().body(ar_);
	
	
	
	
	
	
	
}


}
