package com.omnia.app.controller;

import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.omnia.app.exception.ResourceNotFoundException;
import com.omnia.app.model.Company;
import com.omnia.app.payload.ApiResponse;
import com.omnia.app.payload.CompanyListResponse;
import com.omnia.app.payload.CompanyRequest;
import com.omnia.app.security.CurrentUser;
import com.omnia.app.security.UserPrincipal;
import com.omnia.app.service.CompanyService;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@GetMapping("/all")
	public List<CompanyListResponse> getCompanies(@CurrentUser UserPrincipal currentUser) {
		return companyService.getAllCompanies();
	}

	@PutMapping("/create")
	public ResponseEntity<?> createCompany(@RequestBody CompanyRequest companyRequest) {
		Company company = companyService.createCompany(companyRequest);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{companyId}")
				.buildAndExpand(company.getId()).toUri();

		return ResponseEntity.created(location).body(new ApiResponse(true, "Company Created Successfully"));
	}

	@DeleteMapping("/{companyId}")
	public ResponseEntity<?> deleteRecipe(@PathVariable long companyId) {
		try {
			companyService.deleteCompany(companyId);
			return ResponseEntity.ok().body(new ApiResponse(true, "Company Deleted Successfully"));
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, ex.getMessage()));
		}
	}

}