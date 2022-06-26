package com.omnia.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.omnia.app.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

	List<Recipe> findAllByCategoryId(long categoryId);
	
	//List<Recipe> findAllByRestCategoryId(long categoryId,Long Restid);
	
	
	@Query("select re from Recipe re where re.restaurant.id =?1")
	List<Recipe>  findRecipeByrestoId(Long res_id);
	
	

}
