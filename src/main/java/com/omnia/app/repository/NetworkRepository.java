package com.omnia.app.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.omnia.app.model.Company;
import com.omnia.app.model.Network;

@Repository
public interface NetworkRepository extends JpaRepository<Network, Long> {

	Optional<Network> findById(Long networkId);
	
	Optional<Network> findByReference(String Reference);

	Page<Network> findByCreatedBy(Long userId, Pageable pageable);

	Page<Network> findByCompany(Company company, Pageable pageable);

	long countByCreatedBy(Long userId); 

}