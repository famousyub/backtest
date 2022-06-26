package com.omnia.app.service;

import java.util.List;

import com.omnia.app.model.Area;



public interface IAreaService {
	
List<Area>  getAllArea();
	
	Area getAreaDetails(Long id);
	
	boolean addArea(Long id,Area area);
	
	boolean deleteArea(Long id);
	boolean updateArea(Long id, Area area);


}
