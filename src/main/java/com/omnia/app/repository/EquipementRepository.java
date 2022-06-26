package com.omnia.app.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.omnia.app.enums.State;
import com.omnia.app.enums.Status;
import com.omnia.app.model.Commande;
import com.omnia.app.model.Equipement;
import com.omnia.app.model.Network;

public interface EquipementRepository extends JpaRepository<Equipement, Long> {

	@Override
	Optional<Equipement> findById(Long baseStationId);

	Page<Equipement> findByCreatedBy(Long userId, Pageable pageable);

	long countByCreatedBy(Long userId);

	Optional<Equipement> findTopByStateAndCommandeAndConnected(State state, Commande commande, boolean connected);

	Optional<Equipement> findByIpAddress(String equipementIpAddress);

}
