package com.omnia.app.payload;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NetworkDetailsResponse {

	private Long id;

	private String reference;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private UserSummary createdBy;

	private String creationDateTime;

	private String country;

	private int totalBaseStations;
	private int totalActiveBaseStations;

	private int totalCPE;
	private int totalActiveCPE;

	private int totalAP;
	private int totalActiveAP;

	private int totalUsers;
	private int totalActiveUsers;

	@JsonProperty("equipements")
	private List<EquipmentListResponse> equipements;

	@JsonProperty("blockedCPE")
	private List<EquipmentListResponse> blockedCPE;

	private int totalBlockedCPE;
	private int totalAuthorizedCPE;

	@JsonProperty("unreachableBS")
	private List<EquipmentListResponse> unreachableBS;

	@JsonProperty("alarms")
	private List<AlarmListResponse> alarms;

	@JsonProperty("feeds")
	private List<FeedsListResponse> feeds;

	public NetworkDetailsResponse() {
		super();
		this.equipements = new ArrayList<EquipmentListResponse>();
		this.blockedCPE = new ArrayList<EquipmentListResponse>();
		this.unreachableBS = new ArrayList<EquipmentListResponse>();
		this.feeds = new ArrayList<FeedsListResponse>();
		this.alarms = new ArrayList<AlarmListResponse>();

	}

	public NetworkDetailsResponse(Long id, String reference, String country, String creationDateTime) {
		super();
		this.id = id;
		this.reference = reference;
		this.country = country;
		this.creationDateTime = creationDateTime;
		this.equipements = new ArrayList<EquipmentListResponse>();
		this.blockedCPE = new ArrayList<EquipmentListResponse>();
		this.unreachableBS = new ArrayList<EquipmentListResponse>();
		this.feeds = new ArrayList<FeedsListResponse>();
		this.alarms = new ArrayList<AlarmListResponse>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<FeedsListResponse> getFeeds() {
		return feeds;
	}

	public void setFeeds(List<FeedsListResponse> feeds) {
		this.feeds = feeds;
	}

	public UserSummary getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UserSummary createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(String creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	public List<EquipmentListResponse> getEquipements() {
		return equipements;
	}

	public void setEquipements(List<EquipmentListResponse> equipements) {
		this.equipements = equipements;
	}

	public List<EquipmentListResponse> getBlockedCPE() {
		return blockedCPE;
	}

	public void setBlockedCPE(List<EquipmentListResponse> blockedCPE) {
		this.blockedCPE = blockedCPE;
	}

	public int getTotalBlockedCPE() {
		return totalBlockedCPE;
	}

	public void setTotalBlockedCPE(int totalBlockedCPE) {
		this.totalBlockedCPE = totalBlockedCPE;
	}

	public int getTotalAuthorizedCPE() {
		return totalAuthorizedCPE;
	}

	public void setTotalAuthorizedCPE(int totalAuthorizedCPE) {
		this.totalAuthorizedCPE = totalAuthorizedCPE;
	}

	public int getTotalBaseStations() {
		return totalBaseStations;
	}

	public void setTotalBaseStations(int totalBaseStations) {
		this.totalBaseStations = totalBaseStations;
	}

	public int getTotalActiveBaseStations() {
		return totalActiveBaseStations;
	}

	public void setTotalActiveBaseStations(int totalActiveBaseStations) {
		this.totalActiveBaseStations = totalActiveBaseStations;
	}

	public int getTotalCPE() {
		return totalCPE;
	}

	public void setTotalCPE(int totalCPE) {
		this.totalCPE = totalCPE;
	}

	public int getTotalActiveCPE() {
		return totalActiveCPE;
	}

	public void setTotalActiveCPE(int totalActiveCPE) {
		this.totalActiveCPE = totalActiveCPE;
	}

	public int getTotalAP() {
		return totalAP;
	}

	public void setTotalAP(int totalAP) {
		this.totalAP = totalAP;
	}

	public int getTotalActiveAP() {
		return totalActiveAP;
	}

	public void setTotalActiveAP(int totalActiveAP) {
		this.totalActiveAP = totalActiveAP;
	}

	public int getTotalUsers() {
		return totalUsers;
	}

	public void setTotalUsers(int totalUsers) {
		this.totalUsers = totalUsers;
	}

	public int getTotalActiveUsers() {
		return totalActiveUsers;
	}

	public void setTotalActiveUsers(int totalActiveUsers) {
		this.totalActiveUsers = totalActiveUsers;
	}

	public List<EquipmentListResponse> getUnreachableBS() {
		return unreachableBS;
	}

	public void setUnreachableBS(List<EquipmentListResponse> unreachableBS) {
		this.unreachableBS = unreachableBS;
	}

	public List<AlarmListResponse> getAlarms() {
		return alarms;
	}

	public void setAlarms(List<AlarmListResponse> alarms) {
		this.alarms = alarms;
	}

	public void addEquipment(EquipmentListResponse equipment) {
		equipements.add(equipment);
	}

	public void addBlockedCPE(EquipmentListResponse cpe) {
		blockedCPE.add(cpe);
	}

	public void addUnreachableBS(EquipmentListResponse bs) {
		unreachableBS.add(bs);
	}

	public void addFeed(FeedsListResponse feed) {
		feeds.add(feed);
	}

	public void addAlarm(AlarmListResponse alarm) {
		alarms.add(alarm);
	}

}