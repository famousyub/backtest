package com.omnia.app.util;

import com.omnia.app.enums.AlarmLevel;
import com.omnia.app.enums.Status;
import com.omnia.app.model.Employee;
import com.omnia.app.model.Equipement;
import com.omnia.app.model.Network;
import com.omnia.app.payload.AlarmListResponse;
import com.omnia.app.payload.EquipmentListResponse;
import com.omnia.app.payload.FeedsListResponse;
import com.omnia.app.payload.NetworkDetailsResponse;
import com.omnia.app.payload.NetworkListResponse;
import com.omnia.app.payload.NetworkResponse;
import com.omnia.app.payload.UserSummary;

public class NetworkMapper {

	// @Autowired
	// private static NetworkStatusResponseService networkStatusResponseService;

	public static NetworkListResponse mapNetworkToNetworkListResponse(Network network, Employee creator) {

		NetworkListResponse response = new NetworkListResponse();
		response.setId(network.getId());
		response.setReference(network.getReference());
		response.setCreationDateTime(network.getCreatedAt().toString());
		response.setCountry(network.getCountry());

		UserSummary createdBy = new UserSummary();
		if (creator == null) {
			createdBy = new UserSummary((long) 0, "", "", "", "");
		} else {
			createdBy.setId(creator.getId());
			createdBy.setFirstName(creator.getFirstName());
			createdBy.setLastName(creator.getLastName());
			createdBy.setUsername(creator.getUsername());

		}

		response.setCreatedBy(createdBy);

		for (Equipement eq : network.getEquipements()) {

			response.setTotalBaseStations(response.getTotalBaseStations() + 1);

			if (eq.getStatus() == Status.GIVEN_TO_CLIENT) {
				response.setTotalActiveBaseStations(response.getTotalActiveBaseStations() + 1);
			}

		}

		return response;
	}

	public static NetworkDetailsResponse mapNetworkToNetworkDetailsResponse(Network network, Employee creator) {

		NetworkDetailsResponse networkDetailsResponse = new NetworkDetailsResponse();
		networkDetailsResponse.setId(network.getId());
		networkDetailsResponse.setReference(network.getReference());
		networkDetailsResponse.setCountry(network.getCountry());
		networkDetailsResponse.setCreationDateTime(network.getCreatedAt().toString());

		UserSummary createdBy = new UserSummary();
		createdBy.setId(creator.getId());
		createdBy.setFirstName(creator.getFirstName());
		createdBy.setLastName(creator.getLastName());
		createdBy.setUsername(creator.getUsername());

		networkDetailsResponse.setCreatedBy(createdBy);

		for (Equipement eq : network.getEquipements()) {

			networkDetailsResponse.setTotalBaseStations(networkDetailsResponse.getTotalBaseStations() + 1);

			if (eq.getStatus() == Status.WAITING_ON_TOP) {
				networkDetailsResponse
						.setTotalActiveBaseStations(networkDetailsResponse.getTotalActiveBaseStations() + 1);
			}

			EquipmentListResponse eqListResponse = new EquipmentListResponse();

			eqListResponse.setId(eq.getId());
			eqListResponse.setName(eq.getName());
			eqListResponse.setLogin(eq.getLogin());
			eqListResponse.setPassword(eq.getPassword());
			eqListResponse.setConnected(eq.isConnected());
			
			eqListResponse.setAlarm(eq.isAlarm());
			eqListResponse.setState(eq.getState());
			eqListResponse.setCommande(eq.getCommande());

			eqListResponse.setCreatedAt(eq.getCreatedAt().toString());

			eqListResponse.setStatus(eq.getStatus());
			eqListResponse.setIpAddress(eq.getIpAddress());
			eqListResponse.setBatteryLevel(eq.getBatteryLevel());

			eqListResponse.setMacAddress("d4:38:9c:e0:bd:b8");
			eqListResponse.setModel("wpj563");
			eqListResponse.setFrequency("5.1Ghz");
			eqListResponse.setChannel("100");
			eqListResponse.setChannelWidth("40Mhz");

			eqListResponse.setUpTime("1hour");
			eqListResponse.setNumberOfalarms(1);
			eqListResponse.setSignalQuality(75.5f);

			networkDetailsResponse.addEquipment(eqListResponse);

		}

		AlarmListResponse alarm_1 = new AlarmListResponse(1L, AlarmLevel.critical);
		AlarmListResponse alarm_2 = new AlarmListResponse(2L, AlarmLevel.critical);
		AlarmListResponse alarm_3 = new AlarmListResponse(3L, AlarmLevel.major);
		AlarmListResponse alarm_4 = new AlarmListResponse(4L, AlarmLevel.information);

		networkDetailsResponse.addAlarm(alarm_1);
		networkDetailsResponse.addAlarm(alarm_2);
		networkDetailsResponse.addAlarm(alarm_3);
		networkDetailsResponse.addAlarm(alarm_4);

		FeedsListResponse feed_1 = new FeedsListResponse("02/04/2020", "Admin", "added the network", 1L, "Network");
		FeedsListResponse feed_2 = new FeedsListResponse("02/04/2020", "Admin", "added a base station", 1L,
				"Base Station");
		FeedsListResponse feed_3 = new FeedsListResponse("02/04/2020", "Admin", "updated the network", 1L, "Network");

		networkDetailsResponse.addFeed(feed_1);
		networkDetailsResponse.addFeed(feed_2);
		networkDetailsResponse.addFeed(feed_3);

		networkDetailsResponse.setTotalBlockedCPE(networkDetailsResponse.getBlockedCPE().size());
		/*
		 * BandwidthResponse bandwidth_1 = new BandwidthResponse("days",
		 * "today");bandwidth_1.setData(new
		 * float[]{0.1f,3.3f,4.6f,0.5f,1.1f,0.3f,1.6f,0.5f,0.1f,3.3f,4.6f,0.5f});
		 * 
		 * BandwidthResponse bandwidth_2 = new BandwidthResponse("days",
		 * "yesterday");bandwidth_2.setData(new
		 * float[]{0.1f,0.5f,0.1f,3.3f,4.6f,3.3f,4.6f,0.5f,1.1f,0.3f,1.6f,0.5f});
		 * 
		 * BandwidthResponse bandwidth_3 = new BandwidthResponse("months",
		 * "currentMonth");bandwidth_3.setData(new
		 * float[]{1.6f,0.5f,0.1f,3.3f,0.1f,3.3f,4.6f,0.5f,1.1f,0.3f,4.6f,0.5f});
		 * 
		 * BandwidthResponse bandwidth_4 = new BandwidthResponse("months",
		 * "previousMonth");
		 */
		return networkDetailsResponse;

	}

	public static NetworkResponse mapNetworkToNetworkResponse(Network network) {

		NetworkResponse networkResponse = new NetworkResponse();
		networkResponse.setId(network.getId());
		networkResponse.setReference(network.getReference());
		networkResponse.setCountry(network.getCountry());

		return networkResponse;
	}
}