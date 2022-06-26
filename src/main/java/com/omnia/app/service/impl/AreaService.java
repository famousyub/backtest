package com.omnia.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omnia.app.model.Area;

import com.omnia.app.model.Company;
import com.omnia.app.repository.AreaRepository;

import com.omnia.app.repository.CompanyRepository;
import com.omnia.app.service.IAreaService;
@Service
public class AreaService implements IAreaService {

	@Autowired 
	AreaRepository arearepo;
	@Autowired
CompanyRepository comprepos;
	@Override
	public List<Area> getAllArea() {
		// TODO Auto-generated method stub
		
		List<Area>  listare = new ArrayList<Area>();
		
		arearepo.findAll().forEach(el -> listare.add(el));
		return listare;
		
		
		//return null;
	}

	@Override
	public Area getAreaDetails(Long id) {
		// TODO Auto-generated method stub
		
		return arearepo.findById(id).get();
		//return null;
	}

	@Override
	public boolean addArea(Long id ,Area area) {
		// TODO Auto-generated method stub
		Company optcom = comprepos.findById(id).get();
		
		
		area.setCompany(optcom);
		
		Area s = arearepo.save(area);
		return  s==null ?false :true;
		//return false;
	}

	@Override
	public boolean deleteArea(Long id) {
		// TODO Auto-generated method stub
		//return false;
		
		Area ar =  arearepo.findById(id).get();
		if(ar!=null) {
			arearepo.delete(ar);
			return true ;
		}
		return false;
		
	}

	@Override
	public boolean updateArea(Long id, Area area) {
		// TODO Auto-generated method stub
		return false;
	}

}
