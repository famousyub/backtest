package com.omnia.app.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.omnia.app.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

	
	Optional<Company> findById(Long companyId);
	
	Optional<Company> findByName(String name);

	Page<Company> findByCreatedBy(Long userId, Pageable pageable);

	long countByCreatedBy(Long userId);

}