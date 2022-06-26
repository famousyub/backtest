package com.omnia.app.util;

import com.omnia.app.model.Equipement;
import com.omnia.app.payload.EquipementResponse;

public class EquipementMapper {

	public static EquipementResponse mapEquipementToEquipementResponse(Equipement equipement) {

		EquipementResponse equipementResponse = new EquipementResponse();
		equipementResponse.setId(equipement.getId());
		equipementResponse.setName(equipement.getName());
		equipementResponse.setLogin(equipement.getLogin());
		equipementResponse.setPassword(equipement.getPassword());
		equipementResponse.setIpAddress(equipement.getIpAddress());
		equipementResponse.setStatus(equipement.getStatus());
		equipementResponse.setState(equipement.getState());
		equipementResponse.setLastSeen(equipement.getLastSeen());

		equipementResponse.setAlarm(equipement.isAlarm());

		equipementResponse.setNetwork(NetworkMapper.mapNetworkToNetworkResponse(equipement.getNetwork()));
		equipementResponse.setCreatedAt(equipement.getCreatedAt().toString());
		equipementResponse.setMacAddress("d4:38:9c:e0:bd:b8");
		equipementResponse.setModel("wpj563");
		equipementResponse.setFrequency("5.1Ghz");
		equipementResponse.setChannel("100");
		equipementResponse.setChannelWidth("40Mhz");

		equipementResponse.setUpTime("1hour");
		equipementResponse.setNumberOfalarms(1);
		equipementResponse.setSignalQuality(75.5f);

		return equipementResponse;
	}
}
