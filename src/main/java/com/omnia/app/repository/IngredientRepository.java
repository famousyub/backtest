package com.omnia.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omnia.app.model.Category;

public interface IngredientRepository extends JpaRepository<Category, Long> {

}
