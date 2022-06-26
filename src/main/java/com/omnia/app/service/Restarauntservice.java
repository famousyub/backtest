package com.omnia.app.service;

import java.util.List;

import com.omnia.app.model.Restaurant;
import com.omnia.app.response.RestoResponse;

public interface Restarauntservice {
	
	public Restaurant  createResto(Restaurant res , Long companyId);
	
	public List<Restaurant>  getAllResto(Long companyId);
	
	
	
	public List<RestoResponse>  getRestoswitcher(Long companyId);
	public RestoResponse getRestoById(Long compId , Long restoId);
	

	
	
	

}
