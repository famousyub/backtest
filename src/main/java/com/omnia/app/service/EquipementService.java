package com.omnia.app.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omnia.app.enums.State;
import com.omnia.app.enums.Status;
import com.omnia.app.exception.BadRequestException;
import com.omnia.app.exception.ResourceNotFoundException;
import com.omnia.app.model.Commande;
import com.omnia.app.model.Equipement;
import com.omnia.app.model.Network;
import com.omnia.app.payload.EquipementRequest;
import com.omnia.app.payload.EquipementResponse;
import com.omnia.app.repository.EquipementRepository;
import com.omnia.app.repository.NetworkRepository;
import com.omnia.app.util.EquipementMapper;

@Service
public class EquipementService {

	private static final Logger logger = LoggerFactory.getLogger(EquipementService.class);

	@Autowired
	private EquipementRepository equipementRepository;

	@Autowired
	private NetworkRepository networkRepository;

	public void addEquipementList(List<EquipementRequest> BSList, Long networkId) {

		logger.info("SV_EquipementService_FN_addEquipementList");

		Network network = networkRepository.findById(networkId)
				.orElseThrow(() -> new ResourceNotFoundException("Network", "id", networkId));

		List<Equipement> equipements = new ArrayList<>();

		for (EquipementRequest equipementRequest : BSList) {

			Equipement equipement = new Equipement();
			equipement.setLogin(equipementRequest.getLogin());
			equipement.setPassword(equipementRequest.getPassword());
			equipement.setIpAddress(equipementRequest.getIpAddress());
			equipement.setFirmwareVersion("");
			equipement.setSerialNumber("");
			equipement.setName(equipementRequest.getName());
			equipement.setLastSeen(LocalDateTime.now());
			equipement.setModel("");

			equipement.setNetwork(network);
			equipements.add(equipement);
		}
		equipementRepository.saveAll(equipements);
	}

	public void deleteEquipement(Long equipementId) {

		logger.info("SV_EquipementService_FN_deleteEquipement");

		Equipement equipement = equipementRepository.findById(equipementId)
				.orElseThrow(() -> new ResourceNotFoundException("Equipement", "id", equipementId));
		
		// Detach command
		if(equipement.getCommande()!=null) {
			Commande commande = equipement.getCommande();
			commande.setEquipement(null);
			equipement.setCommande(null);
		}


		equipementRepository.deleteById(equipementId);
	}

	public void editEquipementNetwork(Long equipementId, Long networkId) {

		logger.info("SV_EquipementService_FN_editEquipementNetwork");

		Equipement equipement = equipementRepository.findById(equipementId)
				.orElseThrow(() -> new ResourceNotFoundException("Equipement", "id", equipementId));

		Network network = networkRepository.findById(networkId)
				.orElseThrow(() -> new ResourceNotFoundException("Network", "id", networkId));

		if (equipement.getNetwork() != network) {
			equipement.setNetwork(network);
			equipementRepository.save(equipement);
		} else {
			throw new BadRequestException("Equipement is already attached to this Network");
		}
	}

	public Equipement getEquipementById(long equipementId) {

		logger.info("SV_EquipementService_FN_getEquipementById");

		Equipement equipement = equipementRepository.findById(equipementId)
				.orElseThrow(() -> new ResourceNotFoundException("Equipement", "id", equipementId));

		return equipement;
	}

	public EquipementResponse getEquipementById(Long equipementId) {
		logger.info("SV_EquipementService_FN_getEquipementById");

		Equipement equipement = equipementRepository.findById(equipementId)
				.orElseThrow(() -> new ResourceNotFoundException("Equipement", "id", equipementId));

		return EquipementMapper.mapEquipementToEquipementResponse(equipement);
	}

	public void deleteAll() {
		equipementRepository.deleteAll();
	}

	public Equipement updateEquipement(Long equipementId, EquipementRequest equipementRequest) {

		logger.info("SV_EquipementService_FN_updateEquipement");

		Equipement equipement = equipementRepository.findById(equipementId)
				.orElseThrow(() -> new ResourceNotFoundException("Equipement", "id", equipementId));

		equipement.setName(equipementRequest.getName());
		equipement.setLogin(equipementRequest.getLogin());
		equipement.setPassword(equipementRequest.getPassword());
		equipement.setIpAddress(equipementRequest.getIpAddress());
		
		equipement.setAlarm(equipementRequest.isAlarm());
		
		System.out.println(equipementRequest.isAlarm());

		return equipementRepository.save(equipement);
	}


	public Equipement createEquipement(EquipementRequest equipementRequest, Long networkId) {

		logger.info("SV_EquipementService_FN_createEquipement");

		Network network = networkRepository.findById(networkId)
				.orElseThrow(() -> new ResourceNotFoundException("Network", "id", networkId));

		Equipement equipement = new Equipement();
		equipement.setName(equipementRequest.getName());
		equipement.setLogin(equipementRequest.getLogin());
		equipement.setPassword(equipementRequest.getPassword());
		equipement.setIpAddress(equipementRequest.getIpAddress());
		equipement.setConnected(true);
		equipement.setStatus(Status.WAITING);

		equipement.setFirmwareVersion("");
		equipement.setSerialNumber("");
		equipement.setLastSeen(null);
		equipement.setModel("");

		equipement.setNetwork(network);

		return equipementRepository.save(equipement);
	}

	public Equipement getAvailableEquipementToAssociate() {

		logger.info("SV_EquipementService_FN_getAvailableEquipementToAssociate");

		Equipement equipement = equipementRepository
				.findTopByStateAndCommandeAndConnected(State.IN_BANK_ON_TOP, null, true)
				.orElseThrow(() -> new ResourceNotFoundException("Equipement", "State", "IN_BANK_ON_TOP"));

		return equipement;
	}

	public Equipement getEquipementByIpAddress(String equipementIpAddress) {

		Equipement equipement = equipementRepository.findByIpAddress(equipementIpAddress)
				.orElseThrow(() -> new ResourceNotFoundException("Equipement", "IpAddress", equipementIpAddress));
	
		return equipement;
	}
}
