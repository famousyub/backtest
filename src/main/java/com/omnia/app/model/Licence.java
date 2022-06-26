package com.omnia.app.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.omnia.app.enums.LicenceType;
import com.omnia.app.model.audit.UserDateAudit;

@Entity
@Table(name = "licences")
public class Licence extends UserDateAudit {

	private static final long serialVersionUID = 7475471981177880114L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 140)
	private String activationKey;

	@NotBlank
	@Size(max = 140)
	private String expirationDate;

	private LicenceType type;

	private int equipementLimit;

	private int userLimit;

	private int employeeLimit;

	private boolean portalLicence;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "company_id", nullable = false)
	private Company company;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getActivationKey() {
		return activationKey;
	}

	public void setActivationKey(String activationKey) {
		this.activationKey = activationKey;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public LicenceType getType() {
		return type;
	}

	public void setType(LicenceType type) {
		this.type = type;
	}

	public int getEquipementLimit() {
		return equipementLimit;
	}

	public void setEquipementLimit(int equipementLimit) {
		this.equipementLimit = equipementLimit;
	}

	public int getUserLimit() {
		return userLimit;
	}

	public void setUserLimit(int userLimit) {
		this.userLimit = userLimit;
	}

	public int getEmployeeLimit() {
		return employeeLimit;
	}

	public void setEmployeeLimit(int employeeLimit) {
		this.employeeLimit = employeeLimit;
	}

	public boolean isPortalLicence() {
		return portalLicence;
	}

	public void setPortalLicence(boolean portalLicence) {
		this.portalLicence = portalLicence;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

}