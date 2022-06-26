package com.omnia.app.util;

import com.omnia.app.model.Commande;
import com.omnia.app.model.Equipement;
import com.omnia.app.payload.EquipementCommResponse;

public class EquipementCommMapper {

	public static EquipementCommResponse mapEquipementToEquipementCommResponse(Equipement equipement) {

		EquipementCommResponse equipementCommResponse = new EquipementCommResponse();

		equipementCommResponse.setStatus(equipement.getStatus());
		equipementCommResponse.setAlarm(equipement.isAlarm());

		if (equipement.getCommande() != null) {
			Commande commande = equipement.getCommande();
			System.out.println(commande.getId());
			equipementCommResponse.setNumber(commande.getNumber());
			equipementCommResponse.setWaitingTime(commande.getWaitingTime());
			equipementCommResponse.setTableRef(commande.getTableRef());

		}

		return equipementCommResponse;
	}
}
