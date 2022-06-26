//package com.omnia.app.controller;
//
//import java.io.IOException;
//import java.net.URI;
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
//import com.omnia.app.model.Companies;
//import com.omnia.app.payload.ApiResponse;
//import com.omnia.app.service.ICompaniesService;
//
//
//
//
//@RestController
//@RequestMapping("/api/company")
//@CrossOrigin("*")
//public class CompaniesController {
//	
//	@Autowired 
//	ICompaniesService comservice ;
//	
//	private static final Logger logger = LoggerFactory.getLogger(CompaniesController.class);
//	
//	private byte[] img ;
//	
//	
//	@PostMapping("/upload")
//	public String  uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
//		
//		logger.info("upload image for  products");
//		
//		if(file.getSize()> 1024*1024*1) {
//			return String.format("error  happednde file size too large %s",Long.toString(file.getSize())); 
//		}
//		this.img = file.getBytes();
//		
//		return "file successfully upload";
//	}
//	
//	
//	@PostMapping("/addcompany")
//	private ResponseEntity<?> saveCompany(@RequestBody Companies company)   
//	{  
//	  	
//		       if(img!=null) {
//		    	   company.setLogo(img);
//		       }
//			  Companies cm = comservice.AddCompany(company);
//			
//				  URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{companyId}")
//							.buildAndExpand(cm.getId()).toUri();
//
//					return ResponseEntity.created(location).body(new ApiResponse(true, "Company Created Successfully"));
//			  
//			  //else return ResponseEntity.badRequest().body("error  happende");
//	
//	}  
//	
//	@GetMapping("/listcompany")
//	private List<Companies> getLIstcompany(){
//		
//		return comservice.getCompanyList();
//		
//	}
//	
//	@GetMapping("/{companyId}")
//	private ResponseEntity<?> getCmpanyDetail(@PathVariable Long id){
//		
//		Companies cm = comservice.getCompanyDetails(id);
//		 
//		try {
//			return ResponseEntity.ok().body(cm);
//			
//		}catch(Exception ex) {
//			return ResponseEntity.badRequest().body( String.format(" error occuerd  is %s", ex.getStackTrace().toString()));
//		}
//		
//		//eturn 
//		
//		
//	}
//	
//	
//	@DeleteMapping("/deletecompany/{id}")
//	private  boolean deleteCompany(@PathVariable(value = "id") Long id) {
//		return comservice.deleteCompany(id);
//	}
//
//	
//
//}
