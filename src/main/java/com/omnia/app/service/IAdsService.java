package com.omnia.app.service;

import java.util.List;

import com.omnia.app.model.Ads;



public interface IAdsService {
	
List<Ads>  getAllAds();
	
	boolean addAds(Long id ,Ads ad) ;
	
	Ads getAdsDetails(Long id);
	
	boolean deleteAds(Long id);
	boolean updateAds(Long id,Ads ad);

}
