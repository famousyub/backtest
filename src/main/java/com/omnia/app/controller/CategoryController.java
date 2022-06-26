package com.omnia.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omnia.app.exception.ResourceNotFoundException;
import com.omnia.app.model.Category;
import com.omnia.app.model.Company;
import com.omnia.app.payload.ApiResponse;
import com.omnia.app.repository.UserRepository;
import com.omnia.app.security.CurrentUser;
import com.omnia.app.security.UserPrincipal;
import com.omnia.app.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private UserRepository userRepository;

	@PutMapping()
	public ResponseEntity<?> createCategory(@Valid @RequestBody Category categoryRequest, @CurrentUser UserPrincipal currentUser) {

		try {
		
			Company company = userRepository.getOne(currentUser.getId()).getCompany();

			categoryRequest.setCompany(company);
			Category category = categoryService.createCategory(categoryRequest);

			return ResponseEntity.ok().body(new ApiResponse(true, category.getId(), "Category Created Successfully"));

		} catch (ResourceNotFoundException e) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
		} catch (DataIntegrityViolationException ex) {
			return ResponseEntity.badRequest()
					.body(new ApiResponse(false, "A Category with the same name exists already"));
		}

	}

	@PutMapping("/{categoryId}")
	public ResponseEntity<?> updateCategory(@Valid @RequestBody Category categoryRequest,
			@PathVariable long categoryId) {

		try {
			Category category = categoryService.updateCategory(categoryRequest, categoryId);

			return ResponseEntity.ok().body(new ApiResponse(true, category.getId(), "Category Updated Successfully"));

		} catch (ResourceNotFoundException e) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
		} catch (DataIntegrityViolationException ex) {
			return ResponseEntity.badRequest()
					.body(new ApiResponse(false, "A Category with the same name exists already"));
		}

	}

	@PutMapping("/{categoryId}/{level}")
	public ResponseEntity<?> switchLevelCategory(@Valid @RequestBody Category categoryRequest,
			@PathVariable long categoryId, @PathVariable long level) {

		try {
			List<Category> categories = categoryService.switchLevelCategory(categoryRequest, categoryId, level);

			return ResponseEntity.ok()
					.body(new ApiResponse(true, categories.get(0).getId(), "changed levels of categories with IDs : "
							+ categories.get(0).getId() + "/" + categories.get(1).getId()));

		} catch (ResourceNotFoundException e) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
		} catch (DataIntegrityViolationException ex) {
			return ResponseEntity.badRequest()
					.body(new ApiResponse(false, "A Category with the same name exists already"));
		}

	}

	@DeleteMapping("/{categoryId}")
	public ResponseEntity<?> deleteEquipement(@PathVariable long categoryId) {
		try {
			categoryService.deleteCategory(categoryId);
			return ResponseEntity.ok().body(new ApiResponse(true, "Category Deleted Successfully"));
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, ex.getMessage()));
		}
	}

	@GetMapping("/byCompany")
	public List<Category> getCategoriesByCompany(@CurrentUser UserPrincipal currentUser) {
		Company company = userRepository.getOne(currentUser.getId()).getCompany();

		return categoryService.findCategoriesByCompany(company);
	}
	
	@GetMapping("/byCompany/{companyId}")
	public List<Category> getCategoriesByCompanyId(@PathVariable long companyId) {
		return categoryService.findCategoriesByCompanyId(companyId);
	}
	
	@GetMapping("/all")
	public List<Category> getCategories() {
		return categoryService.findAllCategories();
	}

}