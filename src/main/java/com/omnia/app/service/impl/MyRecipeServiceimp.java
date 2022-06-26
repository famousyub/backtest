package com.omnia.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omnia.app.exception.ResourceNotFoundException;
import com.omnia.app.model.Category;
import com.omnia.app.model.Ingredient;
import com.omnia.app.model.Recipe;
import com.omnia.app.model.Restaurant;
import com.omnia.app.repository.CategoryRepository;
import com.omnia.app.repository.RecipeRepository;
import com.omnia.app.repository.RestaurantRepository;
import com.omnia.app.service.MyRecipeService;


@Service
public class MyRecipeServiceimp implements MyRecipeService {

	
	@Autowired
	private RecipeRepository recipeRepository;

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private RestaurantRepository restrepo;
	@Override
	public Recipe createRecipe(Recipe rec, Long restoid, Long catId) {
		// TODO Auto-generated method stub
		
        
		
		Restaurant resto  = restrepo.findById(restoid).orElseThrow(()-> new ResourceNotFoundException("restaurant", "Id", restoid));
		Category category = categoryRepository.findById(catId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", catId));

		rec.setCategory(category);
		
		rec.setRestaurant(resto);

		List<Recipe> recipes = recipeRepository.findAllByCategoryId(catId);
		rec.setLevel(recipes.size() + 1);

		for (Ingredient ingredient : rec.getIngredients()) {
			ingredient.setRecipe(rec);
		}

		return recipeRepository.save(rec);

	}

	@Override
	public List<Recipe> getRecipeResto(Long restId) {
		// TODO Auto-generated method stub
		return recipeRepository.findRecipeByrestoId(restId);
	}

	@Override
	public List<Recipe> getRecipeRestoBycat(Long restid, Long catId) {
		// TODO Auto-generated method stub
		Category category = categoryRepository.findById(catId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", catId));

		return recipeRepository.findRecipeByrestoId(restid);
	}

	@Override
	public Recipe getProrecipe(Long id) {
		
		return recipeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"," with id",id));
	}

}
