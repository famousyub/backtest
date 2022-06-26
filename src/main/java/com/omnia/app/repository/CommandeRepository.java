package com.omnia.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.omnia.app.model.Commande;
import com.omnia.app.model.Company;

public interface CommandeRepository extends JpaRepository<Commande, Long> {

	@Query("SELECT MAX(c.number) FROM Commande c")
	int findMaxNumber();

	int countByNumberLessThanAndPaidTrue(int number);

	List<Commande> findByCompany(Company company);

	@Modifying
	@Transactional
	@Query("delete from Commande c where c.company=:company")
	void deleteByCompany(@Param("company") Company company);

}