package com.omnia.app.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.omnia.app.exception.BadRequestException;
import com.omnia.app.exception.ResourceNotFoundException;
import com.omnia.app.model.Company;
import com.omnia.app.model.CompanyConfig;
import com.omnia.app.payload.CompanyListResponse;
import com.omnia.app.payload.CompanyRequest;
import com.omnia.app.payload.PagedResponse;
import com.omnia.app.repository.CompanyConfigRepository;
import com.omnia.app.repository.CompanyRepository;
import com.omnia.app.repository.UserRepository;
import com.omnia.app.util.AppConstants;
import com.omnia.app.util.CompanyMapper;

@Service
public class CompanyService {

	private static final Logger logger = LoggerFactory.getLogger(CompanyService.class);

	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired 
	private CompanyConfigRepository compconfrepo;

	@Autowired
	private UserRepository userRepository;

	public List<CompanyListResponse> getAllCompanies() {
		logger.info("SR_CompanyService_FN_getAllCompanies");

		List<Company> companies = companyRepository.findAll();

		List<CompanyListResponse> companyListResponse = companies.stream().map(company -> {
			// Employee employee = userRepository.findById(company.getUpdatedBy())
			// .orElseThrow(() -> new ResourceNotFoundException("User", "id",
			// company.getUpdatedBy()));
			return CompanyMapper.mapCompanyToCompanyListResponse(company);
		}).collect(Collectors.toList());

		return companyListResponse;

	}

	public PagedResponse<CompanyListResponse> getAllCompanies(int page, int size) {
		logger.info("SR_CompanyService_FN_getAllCompanies");

		validatePageNumberAndSize(page, size);

		// Retrieve all companies created
		Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
		Page<Company> companies = companyRepository.findAll(pageable);

		if (companies.getNumberOfElements() == 0) {
			return new PagedResponse<>(Collections.emptyList(), companies.getNumber(), companies.getSize(),
					companies.getTotalElements(), companies.getTotalPages(), companies.isLast());
		}

		List<CompanyListResponse> companyListResponse = companies.map(company -> {
			// Employee employee = userRepository.findById(company.getUpdatedBy())
			// .orElseThrow(() -> new ResourceNotFoundException("User", "id",
			// company.getUpdatedBy()));
			return CompanyMapper.mapCompanyToCompanyListResponse(company);
		}).getContent();

		return new PagedResponse<>(companyListResponse, companies.getNumber(), companies.getSize(),
				companies.getTotalElements(), companies.getTotalPages(), companies.isLast());

	}

	public Company createCompany(CompanyRequest companyRequest) {
		logger.info("SR_CompanyService_FN_createCompany");
		
		
		CompanyConfig comconfig =new CompanyConfig();
		

		Company company = new Company();
		company.setName(companyRequest.getName());
		company.setLogo(companyRequest.getLogo());
		company.setLanguage(companyRequest.getLanguage());
		company.setTimeFormat(companyRequest.getTimeFormat());
		company.setDateFormat(companyRequest.getTimeFormat());
		
		comconfig.setCommandCallValidity(1);
		comconfig.setServerCallValidity(0);
		
		compconfrepo.save(comconfig);
		
		company.setConpanyconfig(comconfig);
		
		

		return companyRepository.save(company);
	}

	private void validatePageNumberAndSize(int page, int size) {
		if (page < 0) {
			throw new BadRequestException("Page number cannot be less than zero.");
		}

		if (size > AppConstants.MAX_PAGE_SIZE) {
			throw new BadRequestException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
		}
	}

	public void deleteCompany(long companyId) {
		logger.info("SV_CompanyService_FN_deleteCompany");

		Company company = companyRepository.findById(companyId)
				.orElseThrow(() -> new ResourceNotFoundException("Company", "id", companyId));

		companyRepository.deleteById(companyId);
	}

}
