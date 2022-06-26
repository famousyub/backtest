package com.omnia.app.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omnia.app.enums.CommandState;
import com.omnia.app.enums.Status;
import com.omnia.app.exception.ResourceNotFoundException;
import com.omnia.app.model.Commande;
import com.omnia.app.model.Company;
import com.omnia.app.model.Equipement;
import com.omnia.app.payload.CommandeListResponse;
import com.omnia.app.repository.CommandeRepository;
import com.omnia.app.util.CommandeMapper;

@Service
public class CommandeService {

	private static final Logger logger = LoggerFactory.getLogger(CommandeService.class);

	@Autowired
	private CommandeRepository commandeRepository;

	@Autowired
	private EquipementService equipementService;

	public Commande createCommande(Commande commandeRequest) {

		logger.info("SV_CommandeService_FN_createCommande");

		// setting commande level
		int number;
		try {
			number = commandeRepository.findMaxNumber() + 1;
		} catch (Exception e) {
			number = 1;
		}

		commandeRequest.setNumber(number);
		commandeRequest.setCommandState(CommandState.NOT_PREPARED);
		return commandeRepository.save(commandeRequest);

	}

	public Commande updateCommande(Commande commandeRequest, Long commandeId) {

		logger.info("SV_CommandeService_FN_createCommande");

		Commande commande = commandeRepository.findById(commandeId)
				.orElseThrow(() -> new ResourceNotFoundException("Commande", "id", commandeId));

		commande.setPaid(commandeRequest.isPaid());
		commande.setEmporter(commandeRequest.isEmporter());
		commande.setCommandState(commandeRequest.getCommandState());

		return commandeRepository.save(commande);
	}

	public Commande associateEquipementToCommande(Long commandeId) {

		logger.info("SV_CommandeService_FN_associateEquipementToCommande");

		Commande commande = commandeRepository.findById(commandeId)
				.orElseThrow(() -> new ResourceNotFoundException("Commande", "id", commandeId));

		Equipement equipement = equipementService.getAvailableEquipementToAssociate();

		commande.setEquipement(equipement);
		commande.setCommandState(CommandState.DELIVERED);
		equipement.setCommande(commande);
		equipement.setAlarm(true);
		equipement.setStatus(Status.GIVEN_TO_CLIENT);

		return commandeRepository.save(commande);
	}

	public Commande disassociateEquipementFromCommande(long commandeId) {
		logger.info("SV_CommandeService_FN_disassociateEquipementFromCommande");

		Commande commande = commandeRepository.findById(commandeId)
				.orElseThrow(() -> new ResourceNotFoundException("Commande", "id", commandeId));

		Equipement equipement = equipementService.getEquipementById(commande.getEquipement().getId());

		commande.setEquipement(null);
		equipement.setCommande(null);
		equipement.setAlarm(false);

		// equipement.setStatus(Status.WAITING);

		return commandeRepository.save(commande);
	}

	public void deleteCommande(Long commandeId) {

		logger.info("SV_CommandeService_FN_deleteCommande");

		Commande commande = commandeRepository.findById(commandeId)
				.orElseThrow(() -> new ResourceNotFoundException("Commande", "id", commandeId));

		// detachEquipement
		if (commande.getEquipement() != null) {
			Equipement equipement = commande.getEquipement();
			equipement.setCommande(null);
			commande.setEquipement(null);
		}

		commandeRepository.deleteById(commandeId);

	}



	/*
	 * private static int getRecipesCount(String recipesJson) { int result = 0; try
	 * { ObjectMapper objectMapper = new ObjectMapper();
	 * objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY,
	 * true); Recipe[] recipes = objectMapper.readValue(recipesJson,
	 * Recipe[].class); result = recipes.length; // System.out.println(result); }
	 * catch (IOException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } return result; }
	 */


	public void updateCommandesWaitingTime() {

		logger.info("SV_CommandeService_FN_updateCommandesWaitingTime");

		List<Commande> commandes = commandeRepository.findAll();

		for (Commande commande : commandes) {

			if (commande.isPaid()) {
				int wait = commandeRepository.countByNumberLessThanAndPaidTrue(commande.getNumber());
				commande.setWaitingTime(wait /** getRecipesCount(commande.getRecipesJson()) */
				);
			} else {
				commande.setWaitingTime(0);
			}

		}

		commandeRepository.saveAll(commandes);
	}
	
	public List<CommandeListResponse> getCommandes() {
		logger.info("SV_CommandeService_FN_getCommandes");

		List<Commande> commandes = commandeRepository.findAll();

		List<CommandeListResponse> commandeListResponse = new ArrayList<CommandeListResponse>();

		for (Commande commande : commandes) {
			commandeListResponse.add(CommandeMapper.mapCommandeToCommandeListResponse(commande));
		}

		return commandeListResponse;
	}

	public List<CommandeListResponse> getCommandesByCompany(Company company) {
		logger.info("SV_CommandeService_FN_getCommandesByCompany");

		List<Commande> commandes = commandeRepository.findByCompany(company);

		List<CommandeListResponse> commandeListResponse = new ArrayList<CommandeListResponse>();

		for (Commande commande : commandes) {
			commandeListResponse.add(CommandeMapper.mapCommandeToCommandeListResponse(commande));
		}

		return commandeListResponse;
	}
	
	public void deleteAllCommandes() {
		logger.info("SV_CommandeService_FN_deleteAllCommandes");

		commandeRepository.deleteAll();
	}


	public void deleteCommandesByCompany(Company company) {
		logger.info("SV_CommandeService_FN_deleteCommandesByCompany");
 
		commandeRepository.deleteByCompany(company);	
	}

}
