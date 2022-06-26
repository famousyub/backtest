package com.omnia.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omnia.app.model.ImageModel;

public interface ImageRepository extends JpaRepository<ImageModel, Long> {
	
	Optional<ImageModel> findByName(String name);

}
