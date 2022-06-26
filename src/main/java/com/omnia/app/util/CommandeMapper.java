package com.omnia.app.util;

import com.omnia.app.model.Commande;
import com.omnia.app.payload.CommandeListResponse;

public class CommandeMapper {

	public static CommandeListResponse mapCommandeToCommandeListResponse(Commande commande) {

		CommandeListResponse commandeListResponse = new CommandeListResponse();

		commandeListResponse.setId(commande.getId());
		commandeListResponse.setCommandState(commande.getCommandState());
		commandeListResponse.setPaid(commande.isPaid());
		commandeListResponse.setNumber(commande.getNumber());
		commandeListResponse.setPrice(commande.getPrice());
		commandeListResponse.setTableRef(commande.getTableRef());
		commandeListResponse.setEmporter(commande.isEmporter());

		commandeListResponse.setWaitingTime(commande.getWaitingTime());

		commandeListResponse.setRecipesJson(commande.getRecipesJson());

		if (commande.getEquipement() != null) {
			commandeListResponse
					.setEquipement(EquipementMapper.mapEquipementToEquipementResponse(commande.getEquipement()));
		}

		return commandeListResponse;
	}

}
