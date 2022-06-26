package com.omnia.app.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.omnia.app.exception.ResourceNotFoundException;
import com.omnia.app.model.Company;
import com.omnia.app.model.Employee;
import com.omnia.app.payload.ApiResponse;
import com.omnia.app.payload.PagedResponse;
import com.omnia.app.payload.UploadFileResponse;
import com.omnia.app.payload.UserResponse;
import com.omnia.app.repository.AdminRepository;
import com.omnia.app.repository.CompanyRepository;
import com.omnia.app.repository.RoleRepository;
import com.omnia.app.repository.UserRepository;
import com.omnia.app.security.CurrentUser;
import com.omnia.app.security.UserPrincipal;
import com.omnia.app.service.FileStorageService;
import com.omnia.app.service.UserService;
import com.omnia.app.util.AppConstants;

@RestController
@RequestMapping("/api/admins")
@CrossOrigin("*")
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	AdminRepository adminrepo;
	
	
	 @Autowired
	 private FileStorageService fileStorageService;
	    
	    @PostMapping("/uploadFile")
	    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
	        String fileName = fileStorageService.storeFile(file);

	        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	                .path("/downloadFile/")
	                .path(fileName)
	                .toUriString();

	        return new UploadFileResponse(fileName, fileDownloadUri,
	                file.getContentType(), file.getSize());
	    }

	    @PostMapping("/uploadMultipleFiles")
	    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
	        return Arrays.asList(files)
	                .stream()
	                .map(file -> uploadFile(file))
	                .collect(Collectors.toList());
	    }
	    
	    

	    @GetMapping("/downloadFile/{fileName:.+}")
	    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
	        // Load file as Resource
	        Resource resource = fileStorageService.loadFileAsResource(fileName);

	        // Try to determine file's content type
	        String contentType = null;
	        try {
	            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
	        } catch (IOException ex) {
	            logger.info("Could not determine file type.");
	        }

	        // Fallback to the default content type if type could not be determined
	        if(contentType == null) {
	            contentType = "application/octet-stream";
	        }

	        return ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType(contentType))
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
	                .body(resource);
	    }
	
	
	@GetMapping("/ping")
	@PreAuthorize("hasRole('Admin')")
	ResponseEntity<?> getPingAdmin(@CurrentUser UserPrincipal currentUser){
		
		
		long id = currentUser.getId();
		Employee em= userRepository.findById(id).get() ;
		
		if(em.equals(null)) {
			return ResponseEntity.badRequest().body("unauthorized 301");
		}
		
		return ResponseEntity.ok().body(em);
		
		
	}
	
	@GetMapping("/comadmin")
	@PreAuthorize("hasRole('ADMIN')")
	ResponseEntity<?> getCompanyForAdmin(@CurrentUser UserPrincipal currentUser){
		
	    
		try {
			
			long id  = currentUser.getId();
			Company com = adminrepo.getCompanyAdmin(id);
			return ResponseEntity.ok().body(com);
			
		}catch(Exception  e) {
			
			 	return ResponseEntity.badRequest().body("somethingwrong");
			
		}
		
		
		
	}
	
	
	
	
	
	
	
	

}
