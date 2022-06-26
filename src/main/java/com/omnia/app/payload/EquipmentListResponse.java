package com.omnia.app.payload;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.omnia.app.enums.State;
import com.omnia.app.enums.Status;
import com.omnia.app.model.Commande;

public class EquipmentListResponse {

	private Long id;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String name;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String model;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("status")
	private Status status;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String upTime;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String ipAddress;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String channel;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String channelWidth;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String frequency;

	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	@JsonProperty("alarms")
	private int numberOfalarms;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private LocalDateTime lastSeen;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("macAddress")
	private String macAddress;

	private float signalQuality;

	private String createdAt;

	private String login;

	private String password;

	private float batteryLevel;

	private boolean connected;
	
	private boolean alarm;
	
	private State state;

	private Commande commande;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private NetworkResponse network;

	public EquipmentListResponse() {
		super();
	}

	public EquipmentListResponse(Long id, String macAddress, String ipAddress, String frequency, String channel,
			String channelWidth, String name, String model, Status status, String upTime, int numberOfalarms) {
		super();
		this.id = id;
		this.macAddress = macAddress;
		this.ipAddress = ipAddress;
		this.frequency = frequency;
		this.channel = channel;
		this.channelWidth = channelWidth;
		this.name = name;
		this.model = model;
		this.status = status;
		this.upTime = upTime;
		this.numberOfalarms = numberOfalarms;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getChannelWidth() {
		return channelWidth;
	}

	public void setChannelWidth(String channelWidth) {
		this.channelWidth = channelWidth;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getUpTime() {
		return upTime;
	}

	public void setUpTime(String upTime) {
		this.upTime = upTime;
	}

	public int getNumberOfalarms() {
		return numberOfalarms;
	}

	public void setNumberOfalarms(int numberOfalarms) {
		this.numberOfalarms = numberOfalarms;
	}

	public float getSignalQuality() {
		return signalQuality;
	}

	public void setSignalQuality(float signalQuality) {
		this.signalQuality = signalQuality;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}



	public LocalDateTime getLastSeen() {
		return lastSeen;
	}

	public void setLastSeen(LocalDateTime lastSeen) {
		this.lastSeen = lastSeen;
	}

	public NetworkResponse getNetwork() {
		return network;
	}

	public void setNetwork(NetworkResponse network) {
		this.network = network;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public float getBatteryLevel() {
		return batteryLevel;
	}

	public void setBatteryLevel(float batteryLevel) {
		this.batteryLevel = batteryLevel;
	}

	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public boolean isAlarm() {
		return alarm;
	}

	public void setAlarm(boolean alarm) {
		this.alarm = alarm;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
	

}