package com.omnia.app.controller;
//monorique monolithique
import java.net.URI;
import java.util.Collections;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.omnia.app.enums.RoleName;
import com.omnia.app.exception.AppException;
import com.omnia.app.exception.ResourceNotFoundException;
import com.omnia.app.model.Company;
import com.omnia.app.model.Employee;
import com.omnia.app.model.Role;
import com.omnia.app.payload.ApiResponse;
import com.omnia.app.payload.JwtAuthenticationResponse;
import com.omnia.app.payload.LoginRequest;
import com.omnia.app.payload.SignUpRequest;
import com.omnia.app.repository.CompanyRepository;
import com.omnia.app.repository.RoleRepository;
import com.omnia.app.repository.UserRepository;
import com.omnia.app.security.JwtTokenProvider;
//quarkus
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JwtTokenProvider tokenProvider;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = tokenProvider.generateToken(authentication);
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}

	@PostMapping("/signup/{companyId}")
	public ResponseEntity<?> registerUser(@PathVariable Long companyId,
			@Valid @RequestBody SignUpRequest signUpRequest) {
		try {
			if (userRepository.existsByUsername(signUpRequest.getUsername())) {
				return new ResponseEntity(new ApiResponse(false, "Username is already taken!"), HttpStatus.BAD_REQUEST);
			}

			if (userRepository.existsByEmail(signUpRequest.getEmail())) {
				return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
						HttpStatus.BAD_REQUEST);
			}

			// Creating user's account
			Employee user = new Employee(signUpRequest.getFirstName(), signUpRequest.getLastName(),
					signUpRequest.getUsername(), signUpRequest.getEmail(), signUpRequest.getPassword());

			user.setPassword(passwordEncoder.encode(user.getPassword()));

			Role userRole = roleRepository.findByName(RoleName.ROLE_SUPER_ADMIN)
					.orElseThrow(() -> new AppException("User Role not set."));

			user.setRoles(Collections.singleton(userRole));

			Company company = companyRepository.findById(companyId)
					.orElseThrow(() -> new ResourceNotFoundException("Company", "id", companyId));
			user.setCompany(company);

			Employee result = userRepository.save(user);

			URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{username}")
					.buildAndExpand(result.getUsername()).toUri();

			return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
		}
	}

	@PostMapping("/signup/superadmin/{companyId}")
	public ResponseEntity<?> register(@PathVariable Long companyId, @Valid @RequestBody SignUpRequest signUpRequest) {
		try {
			if (userRepository.existsByUsername(signUpRequest.getUsername())) {
				return new ResponseEntity(new ApiResponse(false, "Username is already taken!"), HttpStatus.BAD_REQUEST);
			}

			if (userRepository.existsByEmail(signUpRequest.getEmail())) {
				return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
						HttpStatus.BAD_REQUEST);
			}

			// Creating user's account
			Employee user = new Employee(signUpRequest.getFirstName(), signUpRequest.getLastName(),
					signUpRequest.getUsername(), signUpRequest.getEmail(), signUpRequest.getPassword());

			user.setPassword(passwordEncoder.encode(user.getPassword()));

			Role userRole = roleRepository.findByName(RoleName.ROLE_SUPER_ADMIN)
					.orElseThrow(() -> new AppException("User Role not set."));

			user.setRoles(Collections.singleton(userRole));

			Company company = companyRepository.findById(companyId)
					.orElseThrow(() -> new ResourceNotFoundException("Company", "id", companyId));
			user.setCompany(company);

			Employee result = userRepository.save(user);

			URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{username}")
					.buildAndExpand(result.getUsername()).toUri();

			return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
		}
	}
}