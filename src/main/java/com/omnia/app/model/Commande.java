package com.omnia.app.model;

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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.omnia.app.enums.CommandState;

@Entity
@Table(name = "commandes", uniqueConstraints = { @UniqueConstraint(columnNames = { "equipement_id" }) })
public class Commande extends DateAudit {

	private static final long serialVersionUID = -4143232651678561L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	private boolean paid;

	private int number;

	private float price;

	private int waitingTime;

	private String tableRef;

	private boolean emporter;

	@Enumerated(EnumType.STRING)
	@NotNull
	private CommandState commandState;

	@Lob
	private String recipesJson;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
	private Equipement equipement;

	
	  @ManyToOne(fetch = FetchType.LAZY, optional = false) 
	  @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false)
	  private Company company;
	 
	public Commande() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getWaitingTime() {
		return waitingTime;
	}

	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
	}

	public String getTableRef() {
		return tableRef;
	}

	public void setTableRef(String tableRef) {
		this.tableRef = tableRef;
	}

	public String getRecipesJson() {
		return recipesJson;
	}

	public void setRecipesJson(String recipesJson) {
		this.recipesJson = recipesJson;
	}

	public Equipement getEquipement() {
		return equipement;
	}

	public void setEquipement(Equipement equipement) {
		this.equipement = equipement;
	}

	public boolean isEmporter() {
		return emporter;
	}

	public void setEmporter(boolean emporter) {
		this.emporter = emporter;
	}

	public CommandState getCommandState() {
		return commandState;
	}

	public void setCommandState(CommandState commandState) {
		this.commandState = commandState;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	

}