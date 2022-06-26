package com.omnia.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omnia.app.repository.CompanyRepository;
import com.omnia.app.repository.RoleRepository;

@Service
public class DefaultDataLoaderService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultDataLoaderService.class);

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private CompanyRepository companyRepository;

	// add user roles

	// add default company

	// add aministrator and omniacom accounts

}
