package com.omnia.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.omnia.app.model.Area;

import com.omnia.app.model.Company;

public interface AreaRepository extends JpaRepository<Area,Long> {
	
@Query("SELECT c FROM  Company  c  WHERE   c. id  = (select e.company.id from Employee e  where e.id = ?1)")
	
	Company getCompanyAdmin(long adminid);


@Query("select ar from  Area ar where ar.company.id =?1 ")
List<Area> getAreabyId(Long id);

}
