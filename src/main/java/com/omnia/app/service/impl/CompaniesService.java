//package com.omnia.app.service.impl;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.omnia.app.model.Companies;
//import com.omnia.app.repository.CompaniesRepository;
//import com.omnia.app.service.ICompaniesService;
//@Service
//public class CompaniesService implements ICompaniesService  {
//
//	
//	@Autowired
//	CompaniesRepository companyRepository;
//	
//	@Override
//	public List<Companies> getCompanyList() {
//		// TODO Auto-generated method stub
//		//return null;
//		
//
//		List<Companies> company = new ArrayList<Companies>();  
//		 companyRepository.findAll().forEach(el->company.add(el)); 
//		return company;
//	}
//
//	@Override
//	public Companies getCompanyDetails(Long id) {
//		// TODO Auto-generated method stub
//		//return null;
//		return companyRepository.findById(id).get();
//	}
//
//	@Override
//	public Companies AddCompany(Companies com) {
//		// TODO Auto-generated method stub
//		//return false;
//		Companies add_com = companyRepository.save(com);
//		
//		return add_com;
//	}
//
//	@Override
//	public boolean deleteCompany(Long id) {
//		// TODO Auto-generated method stub
//		//return false;
//		
//		Companies cm = companyRepository.findById(id).get();
//		
//		if(cm!=null) {
//			companyRepository.delete(cm);
//			return true;
//		} 
//		
//		return false;
//	}
//
//	@Override
//	public boolean updateCompany(Long id) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//}
