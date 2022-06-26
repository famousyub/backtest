package com.omnia.app.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omnia.app.enums.State;
import com.omnia.app.model.Commande;
import com.omnia.app.model.Equipement;
import com.omnia.app.repository.EquipementRepository;

@Service
public class EquipementCommService {

	private static final Logger logger = LoggerFactory.getLogger(EquipementCommService.class);

	@Autowired
	private EquipementRepository equipementRepository;

	@Autowired
	private EquipementService equipementService;

	public Equipement updateEquipementComm(String equipementIpAddress, String state, String macAddress,
			String firmwareVersion) {

		logger.info("SV_EquipementService_FN_updateEquipementState");

		Equipement equipement = equipementService.getEquipementByIpAddress(equipementIpAddress);

		State updatedState = State.valueOf(state);

		// reset equipement if reterned to bank
		if (equipement.getState() == State.NOT_IN_BANK && updatedState != State.NOT_IN_BANK
				&& equipement.getCommande() != null) {
			Commande commande = equipement.getCommande();
			commande.setEquipement(null);
			equipement.setCommande(null);
			equipement.setAlarm(false);
		}

		// desactivate alarm if alarm activated in top bank and user did remove it from
		// bank
		if (equipement.getState() == State.IN_BANK_ON_TOP && updatedState == State.NOT_IN_BANK
				&& equipement.getCommande() != null) {
			equipement.setAlarm(false);
		}

		equipement.setState(State.valueOf(state));
		equipement.setLastSeen(LocalDateTime.now());
		equipement.setMacAddress(macAddress);
		equipement.setFirmwareVersion(firmwareVersion);

		return equipementRepository.save(equipement);
	}

}
