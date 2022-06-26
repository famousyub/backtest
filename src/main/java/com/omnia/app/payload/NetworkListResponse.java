package com.omnia.app.payload;

public class NetworkListResponse {

	private Long id;
	private String reference;
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

	public NetworkListResponse() {
		super();

	
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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

}