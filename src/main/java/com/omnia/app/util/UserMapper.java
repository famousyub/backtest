package com.omnia.app.util;

import java.util.List;
import java.util.stream.Collectors;

import com.omnia.app.model.Employee;
import com.omnia.app.payload.UserResponse;
import com.omnia.app.payload.UserSummary;

public class UserMapper {
	public static UserSummary mapEmployeeToUserSummary(Employee employee) {

		UserSummary userSummary = new UserSummary(employee.getId(), employee.getUsername(), employee.getFirstName(),
				employee.getLastName(), employee.getEmail());

		return userSummary;
	}

	public static UserResponse mapEmployeeToUserResponse(Employee employee) {

		UserResponse userResponse = new UserResponse(employee.getId(), employee.getUsername(), employee.getFirstName(),
				employee.getLastName(), employee.getEmail(), employee.getCompany());

		// convert a list of Role entity into a list of String
		List<String> roles = employee.getRoles().stream().map(role -> String.valueOf(role.getName()))
				.collect(Collectors.toList());

		userResponse.setRoles(roles);

		return userResponse;
	}

}