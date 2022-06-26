package com.omnia.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.omnia.app.model.TableResto;


@Repository
public interface TableRestoRepository extends JpaRepository<TableResto, Long> {
   
	
	  @Query("select tr from TableResto tr where tr.restaurant.id =?1 ")
	  List<TableResto>  getTableInResturant(Long id);
	  
	  
}
