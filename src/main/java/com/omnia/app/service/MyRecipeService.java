package com.omnia.app.service;

import java.util.List;

import com.omnia.app.model.Recipe;

public interface MyRecipeService {
	
	
	
	public Recipe createRecipe(Recipe rec , Long restoid,Long catId);
	
	public List<Recipe>  getRecipeResto(Long restId);
	
	public List<Recipe>  getRecipeRestoBycat(Long restid, Long catId);
	
	
	public Recipe getProrecipe(Long id);
	
	
	

}
