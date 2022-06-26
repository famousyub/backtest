package com.omnia.app.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.omnia.app.enums.State;
import com.omnia.app.enums.Status;
import com.omnia.app.model.audit.UserDateAudit;

@Entity
@Table(name = "equipements", uniqueConstraints = { @UniqueConstraint(columnNames = { "commande_id" }) })
public class Equipement extends UserDateAudit {

	private static final long serialVersionUID = -4143232651678461L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Size(max = 140)
	@Column(unique = true)
	private String name;

	@Size(max = 140)
	private String model;

	@Size(max = 140)
	@Column(unique = true)
	private String serialNumber;

	@Size(max = 140)
	private String firmwareVersion;

	@Column(nullable = true)
	private LocalDateTime lastSeen;

	@Size(max = 140)
	private String login;

	@Size(max = 140)
	private String password;

	@Size(max = 140)
	@Column(unique = true)
	private String macAddress;

	@Size(max = 140)
	@Column(unique = true)
	private String ipAddress;

	@Enumerated(EnumType.STRING)
	@NotNull
	private Status status;

	@Enumerated(EnumType.STRING)
	private State state;

	private float batteryLevel;

	private boolean connected;

	private boolean alarm;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
	private Commande commande;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "network_id", nullable = false)
	private Network network;

	@OneToMany(mappedBy = "equipement", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@Size(min = 0, max = 10)
	@Fetch(FetchMode.SELECT)
	@BatchSize(size = 30)
	private List<Notification> notifications = new ArrayList<>();

	public Equipement() {
		super();
		this.alarm = false;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getFirmwareVersion() {
		return firmwareVersion;
	}

	public void setFirmwareVersion(String firmwareVersion) {
		this.firmwareVersion = firmwareVersion;
	}

	public LocalDateTime getLastSeen() {
		return lastSeen;
	}

	public void setLastSeen(LocalDateTime lastSeen) {
		this.lastSeen = lastSeen;
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

	public Network getNetwork() {
		return network;
	}

	public void setNetwork(Network network) {
		this.network = network;
	}

	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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

	public float getBatteryLevel() {
		return batteryLevel;
	}

	public void setBatteryLevel(float batteryLevel) {
		this.batteryLevel = batteryLevel;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
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

	@Override
	public String toString() {
		return "Equipement [id=" + id + ", name=" + name + ", model=" + model + ", serialNumber=" + serialNumber
				+ ", firmwareVersion=" + firmwareVersion + ", lastSeen=" + lastSeen + ", login=" + login + ", password="
				+ password + ", macAddress=" + macAddress + ", ipAddress=" + ipAddress + ", status=" + status
				+ ", batteryLevel=" + batteryLevel + ", connected=" + connected + ", alarm=" + alarm + ", commande="
				+ commande + ", network=" + network + ", notifications=" + notifications + "]";
	}

}