package com.omnia.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.omnia.app.model.Company;
import com.omnia.app.model.Employee;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Employee, Long> {

	Optional<Employee> findByEmail(String email);

	Optional<Employee> findByUsernameOrEmail(String username, String email);

	List<Employee> findById(List<Long> userIds);

	Optional<Employee> findByUsername(String username);

	Page<Employee> findByCompany(Company company, Pageable pageable);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}