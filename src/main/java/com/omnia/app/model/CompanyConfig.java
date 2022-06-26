package com.omnia.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "companyconfig")
public class CompanyConfig extends DateAudit {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
	
	
	@OneToOne(mappedBy = "conpanyconfig")
    private Company company;
	
	private String fcbLink;
	private String  instaLink;
	
	private Long positionLat;
	public CompanyConfig() {
		super();
	}

	public CompanyConfig(Company company, String fcbLink, String instaLink, Long positionLat, Long positionLong,
			Integer serverCallValidity, Integer commandCallValidity, byte[] logo) {
		super();
		this.company = company;
		this.fcbLink = fcbLink;
		this.instaLink = instaLink;
		this.positionLat = positionLat;
		this.positionLong = positionLong;
		this.serverCallValidity = serverCallValidity;
		this.commandCallValidity = commandCallValidity;
		this.logo = logo;
	}

	public CompanyConfig(Long id, Company company, String fcbLink, String instaLink, Long positionLat,
			Long positionLong, Integer serverCallValidity, Integer commandCallValidity, byte[] logo) {
		super();
		this.id = id;
		this.company = company;
		this.fcbLink = fcbLink;
		this.instaLink = instaLink;
		this.positionLat = positionLat;
		this.positionLong = positionLong;
		this.serverCallValidity = serverCallValidity;
		this.commandCallValidity = commandCallValidity;
		this.logo = logo;
	}

	private Long positionLong;
	
	private Integer serverCallValidity;
	
	
	private Integer commandCallValidity;
	
	   @Lob
	   @Column(name="logo",nullable = true,updatable=true)
	   private byte[] logo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getFcbLink() {
		return fcbLink;
	}

	public void setFcbLink(String fcbLink) {
		this.fcbLink = fcbLink;
	}

	public String getInstaLink() {
		return instaLink;
	}

	public void setInstaLink(String instaLink) {
		this.instaLink = instaLink;
	}

	public Long getPositionLat() {
		return positionLat;
	}

	public void setPositionLat(Long positionLat) {
		this.positionLat = positionLat;
	}

	public Long getPositionLong() {
		return positionLong;
	}

	public void setPositionLong(Long positionLong) {
		this.positionLong = positionLong;
	}

	public Integer getServerCallValidity() {
		return serverCallValidity;
	}

	public void setServerCallValidity(Integer serverCallValidity) {
		this.serverCallValidity = serverCallValidity;
	}

	public Integer getCommandCallValidity() {
		return commandCallValidity;
	}

	public void setCommandCallValidity(Integer commandCallValidity) {
		this.commandCallValidity = commandCallValidity;
	}

	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}
	   
	
	

	
	

}
