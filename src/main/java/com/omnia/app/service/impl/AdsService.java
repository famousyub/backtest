package com.omnia.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omnia.app.model.Ads;
import com.omnia.app.model.Company;
import com.omnia.app.repository.AdsRepository;

import com.omnia.app.repository.CompanyRepository;
import com.omnia.app.service.IAdsService;
@Service
public class AdsService implements IAdsService {
	
	
	@Autowired
	AdsRepository adsrepo;
	
	@Autowired 
	CompanyRepository comporepo;
	
	@Override
	public List<Ads> getAllAds() {
		// TODO Auto-generated method stub
		//return null;
		
		List<Ads>  listAds =  new ArrayList<Ads>();
		
		adsrepo.findAll().forEach(ad->listAds.add(ad));
		
		return listAds;
	}

	@Override
	public boolean addAds(Long id ,Ads ad) {
		// TODO Auto-generated method stub
Optional<Company> optcom = comporepo.findById(id);
		
		if(!optcom.isPresent()) {
			return false;
		}
		ad.setCompany(optcom.get());
		Ads ad1=adsrepo.save(ad);
		if(ad1!=null) {
			return true;
		}
		return false;
	}

	@Override
	public Ads getAdsDetails(Long id) {
		// TODO Auto-generated method stub
		//return null;
		
		return adsrepo.findById(id).get();
	}

	@Override
	public boolean deleteAds(Long id) {
		// TODO Auto-generated method stub
		//return false;
		
	Ads ad =  adsrepo.findById(id).get();
	
	if(ad!=null) {
		adsrepo.delete(ad);
		return true;
	}
	return false;
	}

	@Override
	public boolean updateAds(Long id, Ads ad) {
		// TODO Auto-generated method stub
		return false;
	}

}
