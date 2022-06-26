package com.omnia.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.omnia.app.model.Ads;
@Repository
public interface AdsRepository  extends JpaRepository<Ads, Long>{

}
