package com.omnia.app.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omnia.app.exception.ResourceNotFoundException;
import com.omnia.app.model.Category;
import com.omnia.app.model.Company;
import com.omnia.app.repository.CategoryRepository;

@Service
public class CategoryService {

	private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

	@Autowired
	private CategoryRepository categoryRepository;

	public Category createCategory(Category categoryRequest) {

		logger.info("SV_CategoryService_FN_createCategory");

		List<Category> categories = categoryRepository.findAll();
		categoryRequest.setLevel(categories.size() + 1);

		return categoryRepository.save(categoryRequest);
	}

	public Category updateCategory(Category categoryRequest, long categoryId) {

		logger.info("SV_CategoryService_FN_updateCategory");

		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

		category.setName(categoryRequest.getName());
		category.setShown(categoryRequest.isShown());
		category.setImagePath(categoryRequest.getImagePath());

		return categoryRepository.save(category);
	}

	public List<Category> switchLevelCategory(Category categoryRequest, long categoryId, long level) {

		logger.info("SV_CategoryService_FN_switchLevelCategory");

		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

		long categoryLevel = category.getLevel();
		long categoryRepLevel = category.getLevel() + level;

		Category categoryReplace = categoryRepository.findByLevel(categoryRepLevel)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "level", categoryRepLevel));

		category.setLevel(categoryRepLevel);
		categoryReplace.setLevel(categoryLevel);

		List<Category> categories = new ArrayList<Category>();
		categories.add(category);
		categories.add(categoryReplace);

		return categoryRepository.saveAll(categories);
	}
	
	public List<Category> findCategoriesByCompany(Company company) {
		logger.info("SV_CategoryService_FN_findCategoriesByCompany");
		List<Category> categories = categoryRepository.findByCompany(company);
		return categories;
	}
	
	public List<Category> findCategoriesByCompanyId(long companyId) {
		logger.info("SV_CategoryService_FN_findCategoriesByCompanyId");
		List<Category> categories = categoryRepository.findByCompanyId(companyId);
		return categories;
	}

	public List<Category> findAllCategories() {
		logger.info("SV_CategoryService_FN_findAllCategories");

		List<Category> categories = categoryRepository.findAll();
		return categories;
	}

	public void deleteCategory(Long categoryId) {

		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

		categoryRepository.deleteById(categoryId);

		List<Category> categories = findAllCategories();

		Comparator<Category> c = (s1, s2) -> (int) s1.getLevel() - (int) s2.getLevel();
		categories.sort(c);

		int index = 1;
		for (Category cat : categories) {
			cat.setLevel(index);
			index++;
		}
		categoryRepository.saveAll(categories);

	}

}
