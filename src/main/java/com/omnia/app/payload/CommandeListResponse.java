package com.omnia.app.payload;

import com.omnia.app.enums.CommandState;

public class CommandeListResponse {

	private long id;
	private CommandState commandState;
	private boolean paid;
	private int number;
	private float price;
	private boolean emporter;
	private int waitingTime;
	private String tableRef;
	private String recipesJson;
	private EquipementResponse equipement;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public CommandState getCommandState() {
		return commandState;
	}

	public void setCommandState(CommandState commandState) {
		this.commandState = commandState;
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

	public String getRecipesJson() {
		return recipesJson;
	}

	public void setRecipesJson(String recipesJson) {
		this.recipesJson = recipesJson;
	}

	public EquipementResponse getEquipement() {
		return equipement;
	}

	public void setEquipement(EquipementResponse equipement) {
		this.equipement = equipement;
	}

	public boolean isEmporter() {
		return emporter;
	}

	public void setEmporter(boolean emporter) {
		this.emporter = emporter;
	}

	public String getTableRef() {
		return tableRef;
	}

	public void setTableRef(String tableRef) {
		this.tableRef = tableRef;
	}

}
