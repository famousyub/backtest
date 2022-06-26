package com.omnia.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omnia.app.model.Role;
import com.omnia.app.repository.RoleRepository;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

	@Autowired
	RoleRepository roleRepository;
	
	@PostMapping
	public Role setRole(@Valid @RequestBody Role role) {
		return roleRepository.save(role);
	}
}
