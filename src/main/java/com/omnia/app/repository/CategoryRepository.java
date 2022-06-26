package com.omnia.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omnia.app.model.Category;
import com.omnia.app.model.Company;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	Optional<Category> findByLevel(long categoryRepLevel);
	
	List<Category> findByCompany(Company company);

	List<Category> findByCompanyId(long companyId);

}
