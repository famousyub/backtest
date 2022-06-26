package com.omnia.app.payload;

import java.util.ArrayList;
import java.util.List;

import com.omnia.app.model.Company;

public class UserResponse {

	private Long id;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private List<String> roles;

	private Company company;
//private Companies company;
	public UserResponse() {
		super();
		this.roles = new ArrayList<>();
	}

	public UserResponse(Long id, String username, String firstName, String lastName, String email, Company company) {
		super();
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.roles = new ArrayList<>();
		this.company = company;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
}
