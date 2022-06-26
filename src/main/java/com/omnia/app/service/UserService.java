package com.omnia.app.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.omnia.app.enums.RoleName;
import com.omnia.app.exception.AppException;
import com.omnia.app.exception.BadRequestException;
import com.omnia.app.exception.ResourceNotFoundException;
import com.omnia.app.model.Company;
import com.omnia.app.model.Employee;
import com.omnia.app.model.Role;
import com.omnia.app.payload.PagedResponse;
import com.omnia.app.payload.RegisterUserRequest;
import com.omnia.app.payload.UserResponse;
import com.omnia.app.repository.CompanyRepository;
import com.omnia.app.repository.RoleRepository;
import com.omnia.app.repository.UserRepository;
import com.omnia.app.util.AppConstants;
import com.omnia.app.util.UserMapper;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	public PagedResponse<UserResponse> getAllUsersByCompany(Long companyID, int page, int size) {

		validatePageNumberAndSize(page, size);

		// Retrieve all users created for the given company
		Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
		Company company = companyRepository.findById(companyID)
				.orElseThrow(() -> new ResourceNotFoundException("Company", "id", companyID));

		Page<Employee> users = userRepository.findByCompany(company, pageable);

		if (users.getNumberOfElements() == 0) {
			return new PagedResponse<>(Collections.emptyList(), users.getNumber(), users.getSize(),
					users.getTotalElements(), users.getTotalPages(), users.isLast());
		}

		List<UserResponse> networkListResponses = users.map(user -> {

			return UserMapper.mapEmployeeToUserResponse(user);
		}).getContent();

		return new PagedResponse<>(networkListResponses, users.getNumber(), users.getSize(), users.getTotalElements(),
				users.getTotalPages(), users.isLast());
	}

	public UserResponse getUserById(Long userID) {

		Employee user = userRepository.findById(userID)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userID));

		return UserMapper.mapEmployeeToUserResponse(user);

	}

	public Employee addUser(RegisterUserRequest registerUserRequest, Long currentUserId) {

		Employee currentEmployee = userRepository.findById(currentUserId)
				.orElseThrow(() -> new ResourceNotFoundException("Current User", "id", currentUserId));

		Company company = companyRepository.findById(registerUserRequest.getCompanyId())
				.orElseThrow(() -> new ResourceNotFoundException("Company", "id", registerUserRequest.getCompanyId()));
		// Company company = currentEmployee.getCompany();

		// generate password automatically
		// String generatedPassword = PasswordGenerator.generatePassword(8, true, true,
		// true, true);

		// Creating user's account
		Employee user = new Employee(registerUserRequest.getFirstName(), registerUserRequest.getLastName(),
				registerUserRequest.getUsername(), registerUserRequest.getEmail(), "password");

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		if (registerUserRequest.getRoles().size() != 0) {
			Set<Role> rolesList = new HashSet<Role>();
			for (String role : registerUserRequest.getRoles()) {

				RoleName roleName = RoleName.valueOf(role);

				Role userRole = roleRepository.findByName(roleName)
						.orElseThrow(() -> new AppException("User Role not set."));
				rolesList.add(userRole);
			}
			user.setRoles(rolesList);
		}

		user.setCompany(company);

		return userRepository.save(user);
	}

	public Employee addUserToCompany(RegisterUserRequest registerUserRequest, Company company) {

		// Creating user's account
		Employee user = new Employee(registerUserRequest.getFirstName(), registerUserRequest.getLastName(),
				registerUserRequest.getUsername(), registerUserRequest.getEmail(), "password");

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		Set<Role> rolesList = new HashSet<Role>();
		RoleName roleName = RoleName.ROLE_ADMIN;
		Role userRole = roleRepository.findByName(roleName).orElseThrow(() -> new AppException("User Role not set."));
		rolesList.add(userRole);

		user.setRoles(rolesList);

		user.setCompany(company);

		return userRepository.save(user);
	}

	public Employee updateUser(Long userID, RegisterUserRequest registerUserRequest) {

		Employee user = userRepository.findById(userID)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userID));

		user.setFirstName(registerUserRequest.getFirstName());
		user.setLastName(registerUserRequest.getLastName());
		user.setUsername(registerUserRequest.getUsername());
		user.setEmail(registerUserRequest.getEmail());
		// user.setPassword(passwordEncoder.encode(registerUserRequest.getPassword()));

		if (registerUserRequest.getRoles().size() != 0) {
			Set<Role> rolesList = new HashSet<Role>();
			for (String role : registerUserRequest.getRoles()) {

				RoleName roleName = RoleName.valueOf(role);

				Role userRole = roleRepository.findByName(roleName)
						.orElseThrow(() -> new AppException("User Role not set."));
				rolesList.add(userRole);
			}
			user.setRoles(rolesList);
		}

		return userRepository.save(user);
	}

	public void deleteUser(Long userID) {

		Employee user = userRepository.findById(userID)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userID));
		userRepository.deleteById(userID);
	}

	private void validatePageNumberAndSize(int page, int size) {
		if (page < 0) {
			throw new BadRequestException("Page number cannot be less than zero.");
		}

		if (size > AppConstants.MAX_PAGE_SIZE) {
			throw new BadRequestException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
		}
	}

}
