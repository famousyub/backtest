package com.omnia.app.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.omnia.app.exception.BadRequestException;
import com.omnia.app.exception.ResourceNotFoundException;

import com.omnia.app.model.Company;
import com.omnia.app.model.Employee;
import com.omnia.app.payload.ApiResponse;
import com.omnia.app.payload.PagedResponse;
import com.omnia.app.payload.RegisterUserRequest;
import com.omnia.app.payload.UserIdentityAvailability;
import com.omnia.app.payload.UserProfile;
import com.omnia.app.payload.UserResponse;
import com.omnia.app.repository.AreaRepository;
import com.omnia.app.repository.CompanyRepository;
import com.omnia.app.repository.RoleRepository;
import com.omnia.app.repository.UserRepository;
import com.omnia.app.security.CurrentUser;
import com.omnia.app.security.UserPrincipal;
import com.omnia.app.service.UserService;
import com.omnia.app.util.AppConstants;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	AreaRepository arepo;
	

	@GetMapping("/me")
	// @PreAuthorize("hasRole('USER')")
	public UserResponse getCurrentUser(@CurrentUser UserPrincipal currentUser) {
		
		Company com = arepo.getCompanyAdmin(currentUser.getId());
		
		UserResponse userResponse = new UserResponse(currentUser.getId(), currentUser.getUsername(),
				currentUser.getFirstName(), currentUser.getLastName(), currentUser.getEmail(), null);

		// convert a list of Authority into a list of String
		List<String> roles = currentUser.getAuthorities().stream().map(authority -> String.valueOf(authority))
				.collect(Collectors.toList());
		userResponse.setRoles(roles);

		return userResponse;
	}

	@GetMapping("/checkUsernameAvailability")
	public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
		Boolean isAvailable = !userRepository.existsByUsername(username);
		return new UserIdentityAvailability(isAvailable);
	}

	@GetMapping("/checkEmailAvailability")
	public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
		Boolean isAvailable = !userRepository.existsByEmail(email);
		return new UserIdentityAvailability(isAvailable);
	}

	@GetMapping("/{username}")
	public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
		Employee user = userRepository.findByUsername(username)
				.orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

		long pollCount = 0;
		long voteCount = 0;

		UserProfile userProfile = new UserProfile(user.getId(), user.getUsername(), user.getFirstName(),
				user.getLastName(), user.getCreatedAt(), pollCount, voteCount);

		return userProfile;
	}

	@GetMapping("/byCompany/{companyId}")
	// @PreAuthorize("hasRole('SUPER_ADMIN')")
	public PagedResponse<UserResponse> getUsersByCompany(@CurrentUser UserPrincipal currentUser,
			@PathVariable Long companyId,
			@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
			@RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
		return userService.getAllUsersByCompany(companyId, page, size);
	}

	@GetMapping("/byId/{userId}")
	@PreAuthorize("hasRole('SUPER_ADMIN')")
	public ResponseEntity<?> getUserById(@CurrentUser UserPrincipal currentUser, @PathVariable Long userId) {
		try {
			UserResponse userResponse = userService.getUserById(userId);
			return ResponseEntity.ok(userResponse);
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, ex.getMessage()));
		}
	}

	@PostMapping("/add")
	@PreAuthorize("hasRole('SUPER_ADMIN')")
	public ResponseEntity<?> addUser(@CurrentUser UserPrincipal currentUser,
			@Valid @RequestBody RegisterUserRequest registerUserRequest) {

		try {
			Employee user = userService.addUser(registerUserRequest, currentUser.getId());
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}")
					.buildAndExpand(user.getId()).toUri();
			return ResponseEntity.created(location)
					.body(new ApiResponse(true, user.getId(), "User Created Successfully"));

		} catch (ResourceNotFoundException e) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
		} catch (DataIntegrityViolationException ex) {
			if (userRepository.existsByUsername(registerUserRequest.getUsername())) {
				return ResponseEntity.badRequest().body(new ApiResponse(false, "Username is already taken!"));

			} else if (userRepository.existsByEmail(registerUserRequest.getEmail())) {
				return ResponseEntity.badRequest().body(new ApiResponse(false, "Email Address already in use!"));
			} else {
				return ResponseEntity.badRequest().body(new ApiResponse(false, "Unable to add new user"));
			}
		}
	}

	@PutMapping("/update/{userId}")
	@PreAuthorize("hasRole('SUPER_ADMIN')")
	public ResponseEntity<?> updateUser(@PathVariable Long userId,
			@Valid @RequestBody RegisterUserRequest registerUserRequest) {
		try {

			Employee user = userService.updateUser(userId, registerUserRequest);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}")
					.buildAndExpand(user.getId()).toUri();

			return ResponseEntity.created(location).body(new ApiResponse(true, "User Updated Successfully"));
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, ex.getMessage()));
		} catch (DataIntegrityViolationException ex) {
			if (userRepository.existsByUsername(registerUserRequest.getUsername())) {
				return ResponseEntity.badRequest().body(new ApiResponse(false, "Username is already taken!"));
			} else {
				return ResponseEntity.badRequest().body(new ApiResponse(false, "Unable to update user"));
			}
		}
	}

	@GetMapping("/update/mypassword/{oldPassword}/{newPassword}")
	public ResponseEntity<?> updateMyUserPassword(@CurrentUser UserPrincipal currentUser,
			@PathVariable String oldPassword, @PathVariable String newPassword) {

		try {
			Employee myAccount = userRepository.findById(currentUser.getId())
					.orElseThrow(() -> new ResourceNotFoundException("Employee", "id", currentUser.getId()));

			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(myAccount.getEmail(), oldPassword));

			myAccount.setPassword(passwordEncoder.encode(newPassword));
			Employee result = userRepository.save(myAccount);

		} catch (Exception e) {
			return new ResponseEntity(new ApiResponse(false, "Verify your current password"), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity(new ApiResponse(true, "password updated successfully"), HttpStatus.ACCEPTED);

	}

	@PutMapping("/update/myaccount")
	public ResponseEntity<?> updateMyAccount(@CurrentUser UserPrincipal currentUser,
			@Valid @RequestBody RegisterUserRequest registerUserRequest) {

		try {
			Employee myAccount = userRepository.findById(currentUser.getId())
					.orElseThrow(() -> new ResourceNotFoundException("Employee", "id", currentUser.getId()));

			myAccount.setFirstName(registerUserRequest.getFirstName());
			myAccount.setLastName(registerUserRequest.getLastName());
			myAccount.setUsername(registerUserRequest.getUsername());
			myAccount.setEmail(registerUserRequest.getEmail());
			Employee result = userRepository.save(myAccount);

			return ResponseEntity.ok().body(new ApiResponse(true, "User Updated Successfully"));
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, ex.getMessage()));
		} catch (DataIntegrityViolationException ex) {
			if (userRepository.existsByUsername(registerUserRequest.getUsername())) {
				return ResponseEntity.badRequest().body(new ApiResponse(false, "Username is already taken!"));
			}
			if (userRepository.existsByEmail(registerUserRequest.getEmail())) {
				return ResponseEntity.badRequest().body(new ApiResponse(false, "Email is already taken!"));
			} else {
				return ResponseEntity.badRequest().body(new ApiResponse(false, "Unable to update user"));
			}
		}

	}

	@DeleteMapping("/delete/{userId}")
	@PreAuthorize("hasRole('SUPER_ADMIN')")
	public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
		try {
			userService.deleteUser(userId);
			return ResponseEntity.ok().body(new ApiResponse(true, "User Deleted Successfully"));
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, ex.getMessage()));
		} catch (BadRequestException e) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, "Unable to delete User"));
		}
	}

	@DeleteMapping("/delete/all")
	public void deleteAll() {
		userRepository.deleteAll();
	}
}