package com.omnia.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omnia.app.model.Company;
import com.omnia.app.model.Restaurant;
import com.omnia.app.repository.UserRepository;
import com.omnia.app.response.RestoResponse;
import com.omnia.app.security.CurrentUser;
import com.omnia.app.security.UserPrincipal;
import com.omnia.app.service.Restarauntservice;

@RestController
@RequestMapping("/api/resto")
public class RestaurantController {
	
	@Autowired
	Restarauntservice resserv;
	
	@Autowired 
	private UserRepository userRepository;
	
	@PostMapping("/create")
	
	public ResponseEntity<?>  createResto(@CurrentUser UserPrincipal currentUser,@RequestBody Restaurant res )
{
		Company company = userRepository.getOne(currentUser.getId()).getCompany();
		Restaurant res_  = resserv.createResto(res, company.getId());
		
		return ResponseEntity.ok().body(res_);
		
		
		
		
	}
	
	@GetMapping("/allrest/{comId}")
	public ResponseEntity<?>  getAllListRes(@PathVariable("comId") Long id){
		  List<Restaurant>  g  = resserv.getAllResto(id);
		return  ResponseEntity.ok().body(g);
		
	}
	
	
	@GetMapping("/restcomp")
	public ResponseEntity<?>  getAllListResconect(@CurrentUser UserPrincipal currentUser){
		 
		Company company = userRepository.getOne(currentUser.getId()).getCompany();
		List<Restaurant>  g  = resserv.getAllResto(company.getId());
		return  ResponseEntity.ok().body(g);
		
	}
	
	@GetMapping("/allrestrecipe")
	public ResponseEntity<?>  getAllListResrecipesconn(@CurrentUser UserPrincipal currentUser){
		Company company = userRepository.getOne(currentUser.getId()).getCompany(); 
		List<RestoResponse>  g  = resserv.getRestoswitcher(company.getId());
		return  ResponseEntity.ok().body(g);
		
	}
	
	@GetMapping("/allrestrecipes/{comId}")
	public ResponseEntity<?>  getAllListResrecipes(@PathVariable("comId") Long id){
		  List<RestoResponse>  g  = resserv.getRestoswitcher(id);
		return  ResponseEntity.ok().body(g);
		
	}
}
