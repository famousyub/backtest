package com.omnia.app.response;

import java.util.List;

import com.omnia.app.model.Recipe;
import com.omnia.app.model.Restaurant;

public class RestoResponse {
	
	private Restaurant  resto ;
	
	private List<Recipe> recipes ;

	public Restaurant getResto() {
		return resto;
	}

	public void setResto(Restaurant resto) {
		this.resto = resto;
	}

	public List<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}
	
	

}
