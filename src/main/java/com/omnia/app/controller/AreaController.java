package com.omnia.app.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.omnia.app.model.Area;

import com.omnia.app.model.Company;
import com.omnia.app.payload.AreaRequest;
import com.omnia.app.repository.AreaRepository;
import com.omnia.app.security.CurrentUser;
import com.omnia.app.security.UserPrincipal;
import com.omnia.app.service.IAreaService;

@RestController
@RequestMapping("/api/area")
@CrossOrigin("*")
public class AreaController {
	
	@Autowired
	IAreaService iareaservice ;
	
	@Autowired
	AreaRepository arepo;
	
	
private byte[] img ;
	
	
	@PostMapping(value="/areupload")
	public void   uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
		
		
		
		/*if(file.getSize()> 1024*1024*1) {
			return ResponseEntity.badRequest().body(String.format("error  happednde file size too large %s",Long.toString(file.getSize())));
			
			
		}*/
		this.img = file.getBytes();
		
		//return ResponseEntity.ok().body("upload sucess");
	}
	
	
	@GetMapping("/listareasp")
	
	public ResponseEntity<?> getAreaslisp(){
		
		List<Area> areas =arepo.findAll();
		
		return ResponseEntity.ok().body(areas);
		
		
		
	}
	
	@PostMapping("/addareap")
	
	ResponseEntity<?> addArea(@RequestBody AreaRequest ar1 , @CurrentUser UserPrincipal currentUser){
		
try {
			
	
	        Area ar  = new Area ();
			long id  = currentUser.getId();
			
			System.out.println(id);
			Company com = arepo.getCompanyAdmin(id);
			
			System.out.println(com.getId());
			
			ar.setName(ar1.getName());
			ar.setLevel(ar1.getLevel());
		    ar.setCompany(com);
			if(!img.equals(null))
			ar.setAreapic(this.img);
			
			arepo.save(ar);
			
			return ResponseEntity.ok().body(ar);
			
		}catch(Exception  e) {
			
			 	return ResponseEntity.badRequest().body("somethingwrong");
			
		}
		
		
		
		
	}
	
	
	@GetMapping("/listarea")
	List<Area>  getAllArea(){
		return  iareaservice.getAllArea();
	}
	
	@PostMapping("/addarea/company/{companyId}")
	boolean addArea(@PathVariable(value="companyId") Long id,@RequestBody Area ar) {
		
		 if(img!=null) {
	    	   ar.setAreapic(img);
	       }
		return iareaservice.addArea(id,ar);
		
		
	}
	@GetMapping("/areadetail/{id}")
	Area getAreaDEtails(@PathVariable Long id) {
		
		  return iareaservice.getAreaDetails(id);
		
	}
	
	
}
