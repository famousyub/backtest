package com.omnia.app.util;

import com.omnia.app.model.Company;
import com.omnia.app.payload.CompanyListResponse;

public class CompanyMapper {

	public static CompanyListResponse mapCompanyToCompanyListResponse(Company company) {

		CompanyListResponse companyListResponse = new CompanyListResponse();
		companyListResponse.setId(company.getId());
		companyListResponse.setName(company.getName());
		companyListResponse.setLanguage(company.getLanguage());
		companyListResponse.setLogo(company.getLogo());

		companyListResponse.setUpdatedAt(company.getUpdatedAt().toString());
		// companyListResponse.setUpdatedBy(employee.getFirstName() + " " +
		// employee.getLastName());

		return companyListResponse;
	}
}